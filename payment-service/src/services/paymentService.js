export const createPaymentIntent = async (order) => {
  // This will later call Razorpay / Stripe
  return {
    paymentId: "PAY_" + Date.now(),
    amount: order.amount,
    currency: "INR",
    status: "CREATED"
  };
};
