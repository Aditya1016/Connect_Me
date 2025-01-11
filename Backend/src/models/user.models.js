import mongoose, { Schema } from "mongoose";
import bcrypt from "bcrypt";

const userSchema = new Schema(
    {
        name: {
            type: String,
            required: true,
        },
        collegeName: {
            type: String,
            required: true,
            index: true,
            enum:[
                "IIT Bombay", "IIT Delhi", "IIT Madras", "IIT Kanpur", "IIT Kharagpur",
                "IIT Roorkee", "IIT Guwahati","IIT Hyderabad","IIT Mandi","IIT Dhanbad","NIT Trichy", "NIT Surathkal",
                "NIT Warangal", "NIT Rourkela", "NIT Calicut", "IIT BHU",
                "NIT Kurukshetra", "NIT Durgapur", "IIT Bhubaneswar", "BIT Mesra"
            ]
        },
        collegeMail: {
            type: String,
            required: true,
            index: true,
            unique: true
        },
        branch: {
            type: String,
            required: true,
        },
        batch: {
            type: String,
            required: true,
        },
        phoneNumber: {
            type: String,
            required: true,
        },
        password: {
            type: String,
            required: true,
        },
        isVerified:{
            type: Boolean,
            default: false
        },
        verificationToken: {
            type: String
        },
        verificationTokenExpiry: {
            type: Date
        }
        // RefreshToken: {
        //     type: String
        // },
        // RefreshTokenExpiry:{
        //     type: Date
        // }
    },
    { timestamps: true }
);

userSchema.pre("save", async function (next) {
    if (!this.isModified("password")) return next();
    const salt = await bcrypt.genSalt(10);
    this.password = await bcrypt.hash(this.password, salt);
    next();
});

userSchema.methods.isPasswordCorrect = async function (password) {
    return await bcrypt.compare(password, this.password);
}

export const User = mongoose.model("User", userSchema);