import { Router } from "express";
import { registerUser, loginUser, verifyUser } from "../controllers/user.controller.js";
import { body } from "express-validator";

const router = Router();

const allowedColleges = [
    "IIT Bombay", "IIT Delhi", "IIT Madras", "IIT Kanpur", "IIT Kharagpur",
    "IIT Roorkee", "IIT Guwahati", "IIT Hyderabad", "IIT Mandi", "IIT Dhanbad",
    "NIT Trichy", "NIT Surathkal", "NIT Warangal", "NIT Rourkela", "NIT Calicut",
    "IIT BHU", "NIT Kurukshetra", "NIT Durgapur", "IIT Bhubaneswar", "BIT Mesra"
];

router.route("/register").post(
    body("name").isString().isLength({ min: 2, max: 20 }).withMessage("Name must be a string between 2 and 20 characters"),
    body("collegeName").isString().custom(value => {
        if (!allowedColleges.includes(value)) {
            throw new Error("Invalid college name");
        }
        return true;
    }).withMessage("College name must be one of the allowed values"),
    body("collegeMail").isEmail().withMessage("College mail must be a valid email"),
    body("branch").isString().withMessage("Branch must be a string"),
    body("batch").isString().isLength({ min: 4, max: 4 }).withMessage("Batch must be a 4-digit number"),
    body("phoneNumber").isString().isLength({ min: 10, max: 10 }).withMessage("Phone number must be a 10-digit number"),
    body("password").isString().isLength({ min: 6, max: 20 }).withMessage("Password must be a string between 6 and 20 characters"),
    registerUser
);

router.route("/login").post(
    body("collegeMail").isEmail().withMessage("College mail must be a valid email"),
    body("password").isString().isLength({ min: 6, max: 20 }).withMessage("Password must be a string between 6 and 20 characters"),
    loginUser
);

router.route("/verify").post(
    body("collegeMail").isEmail().withMessage("College mail must be a valid email"),
    body("verificationToken").isString().isLength({ min: 6, max: 6 }).withMessage("OTP must be a 6-digit number"),
    verifyUser
);

export default router;