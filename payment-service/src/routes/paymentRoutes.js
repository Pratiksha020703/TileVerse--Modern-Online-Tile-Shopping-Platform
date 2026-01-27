import express from "express";
import verifyToken from "../middlewares/authMiddleware.js";
import {
  health,
  initiatePayment
} from "../controllers/paymentController.js";

const router = express.Router();

router.get("/health", health);
router.post("/initiate", verifyToken, initiatePayment);

export default router;
