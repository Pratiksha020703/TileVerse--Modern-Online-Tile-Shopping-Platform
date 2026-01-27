export const health = (req, res) => {
  res.json({
    status: "UP",
    service: "Payment Service",
    time: new Date()
  });
};

export const initiatePayment = (req, res) => {
  const { amount, orderId } = req.body;

  res.json({
    message: "Payment initiated (mock)",
    user: req.user.email,
    amount,
    orderId,
    status: "PENDING"
  });
};
