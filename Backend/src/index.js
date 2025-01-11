import dotenv from "dotenv";
import {app} from "./app.js";
import connectDB from "./db/index.js";

dotenv.config({
    path: './.env'
})

connectDB()
.then(() => {
    app.listen(process.env.PORT || 8080, "0.0.0.0", () => {
        console.log(`⚙️  Server is running on port: ${process.env.PORT}`);
    });
})
.catch((err) => {
    console.log("MONGO DB connection failed !!!", err);
})