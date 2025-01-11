import { asyncHandler } from "../utils/asyncHandler.js";
import {validationResult} from "express-validator"
import crypto from "crypto";
import {User} from "../models/user.models.js";

function generateOTP() {
    return crypto.randomInt(100000, 999999);
}

// const generateRefreshToken = async (userId) => {
//     try {
//         const user = await User.findById(userId);

//         const refreshToken = user.generateRefreshToken();

//         user.RefreshTokenExpiry = Date.now() + 3000000; // refresh token expires in 5 minutes

//         await user.save({ validateBeforeSave: false });

//         return { refreshToken };

//     } catch (error) {
//         console.log(error);
//         throw Response.json(
//             {
//                 success: false,
//                 message: "Failed to generate access and refresh token"
//             },
//             { status: 500 }
//         );
//     }
// }

const registerUser = asyncHandler(async (req, res) => {
    const errors = validationResult(req);
    if (!errors.isEmpty()) {
        return res.status(400).json({ success: false, errors: errors.array() });
    }

    const {name, collegeName, collegeMail, branch, batch, phoneNumber, password} = req.body;

    try {
        const existingUser = await User.findOne({ collegeMail });
        if (existingUser) {
            return res.status(400).json({
                success: false,
                message: "User with this college mail already exists"
            });
        }
        
    
        const verificationToken = generateOTP().toString();
        const verificationTokenExpiry = Date.now() + 3600000;

        // Create a new user
        const newUser = await User.create({
            name,
            collegeName,
            collegeMail,
            branch,
            batch,
            phoneNumber,
            password,
            verificationToken,
            verificationTokenExpiry
        });  
        
        const createdUser = await User.findById(newUser._id).select("-password");

        console.log(createdUser)
        if(!createdUser) {
            return res.status(400).json({
                success: false,
                message: "User not found"
            })
        }
    
        return res.status(201).json({
            success: true,
            message: "User registered successfully",
            user: createdUser
        });
    } catch (error) {
        console.log(error);
        return res.status(500).json({
            success: false,
            message: "Internal server error"
        });
    }

});

const loginUser = asyncHandler(async (req, res) => {
    const errors = validationResult(req);
    if (!errors.isEmpty()) {
        return res.status(400).json({ success: false, errors: errors.array() });
    }

    const {collegeMail, password} = req.body;

    try {
        const user = await User.findOne({ collegeMail });
        if (!user) {
            return res.status(400).json({
                success: false,
                message: "User with this college mail does not exist"
            });
        }

        if (!user.isVerified) {
            return res.status(400).json({
                success: false,
                message: "User is not verified"
            });
        }

        const isPasswordCorrect = await user.isPasswordCorrect(password);
        if (!isPasswordCorrect) {
            return res.status(400).json({
                success: false,
                message: "Incorrect password"
            });
        }

        return res.status(200).json({
            success: true,
            message: "Login successful",
            user
        });
    } catch (error) {
        console.log(error);
        return res.status(500).json({
            success: false,
            message: "Internal server error"
        });
    }
});

const verifyUser = asyncHandler(async (req, res) => {
    const { collegeMail, verificationToken } = req.body;

    try {
        const user = await User.findOne({ collegeMail });
        if (!user) {
            return res.status(400).json({
                success: false,
                message: "User with this college mail does not exist"
            })        
        }

        if (user.verified) {
            return res.status(400).json({
                success: false,
                message: "User is already verified"
            });
        }

        if (user.verificationToken !== verificationToken) {
            return res.status(400).json({
                success: false,
                message: "Invalid verification token"
            });
        }

        if (Date.now() > user.verificationTokenExpiry) {
            return res.status(400).json({
                success: false, 
                message: "Verification token has expired"
            });
        }

        user.isVerified = true;
        user.verificationToken = undefined;
        user.verificationTokenExpiry = undefined;
        await user.save();

        return res.status(200).json({
            success: true,
            message: "User verified successfully"
        });
    } catch (error) {
        console.log(error);
        return res.status(500).json({
            success: false,
            message: "Internal server error"
        });
    }
});

export {
    registerUser,
    loginUser,
    verifyUser
}