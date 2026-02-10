import { Link } from "react-router-dom";
import "../styles/Home.css";

const ContactUs = () => {
    return (
        <>
            {/* Contact Hero */}
            <section className="hero-section text-center text-white d-flex align-items-center justify-content-center">
                <div className="container">
                    <h1 className="display-4 fw-bold mb-3">
                        Contact Us
                    </h1>
                    <p className="lead mb-4">
                        We’d love to help you choose the perfect tiles for your space
                    </p>
                    <Link
                        to="/gallery"
                        className="btn btn-lg btn-coral-outline me-2"
                    >
                        Browse Tiles
                    </Link>
                    <Link
                        to="/"
                        className="btn btn-lg btn-light-outline"
                    >
                        Back to Home
                    </Link>
                </div>
            </section>

            {/* Contact Section */}
            <section className="py-5">
                <div className="container">
                    <div className="row">
                        {/* Contact Form */}
                        <div className="col-md-7 mb-4">
                            <div className="card shadow-sm border-0 h-100">
                                <div className="card-body">
                                    <h4 className="fw-bold mb-4 text-coral">
                                        Send Us a Message
                                    </h4>

                                    <form>
                                        <div className="mb-3">
                                            <label className="form-label fw-semibold">
                                                Full Name
                                            </label>
                                            <input
                                                type="text"
                                                className="form-control"
                                                placeholder="Your name"
                                            />
                                        </div>

                                        <div className="mb-3">
                                            <label className="form-label fw-semibold">
                                                Email Address
                                            </label>
                                            <input
                                                type="email"
                                                className="form-control"
                                                placeholder="you@email.com"
                                            />
                                        </div>

                                        <div className="mb-3">
                                            <label className="form-label fw-semibold">
                                                Phone Number
                                            </label>
                                            <input
                                                type="tel"
                                                className="form-control"
                                                placeholder="+1 234 567 890"
                                            />
                                        </div>

                                        <div className="mb-4">
                                            <label className="form-label fw-semibold">
                                                Message
                                            </label>
                                            <textarea
                                                className="form-control"
                                                rows="4"
                                                placeholder="Tell us about your project..."
                                            ></textarea>
                                        </div>

                                        <button
                                            type="submit"
                                            className="btn btn-coral px-4"
                                        >
                                            Send Message
                                        </button>
                                    </form>
                                </div>
                            </div>
                        </div>

                        {/* Contact Info */}
                        <div className="col-md-5 mb-4">
                            <div className="card shadow-sm border-0 h-100">
                                <div className="card-body">
                                    <h4 className="fw-bold mb-4 text-coral">
                                        Get in Touch
                                    </h4>

                                    <p className="mb-3">
                                        <strong>Showroom:</strong><br />
                                        123 Tile Avenue,<br />
                                        Design City, DC 45678
                                    </p>

                                    <p className="mb-3">
                                        <strong>Phone:</strong><br />
                                        +1 (234) 567-8900
                                    </p>

                                    <p className="mb-3">
                                        <strong>Email:</strong><br />
                                        support@tileverse.com
                                    </p>

                                    <p className="mb-4">
                                        <strong>Working Hours:</strong><br />
                                        Mon – Sat: 9:00 AM – 7:00 PM
                                    </p>

                                    <Link
                                        to="/gallery"
                                        className="btn btn-outline-dark btn-sm"
                                    >
                                        Visit Gallery
                                    </Link>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </section>

            {/* CTA Section */}
            <section className="cta-section py-5 text-white text-center">
                <div className="container">
                    <h2 className="fw-bold mb-3">
                        Need Samples or Expert Advice?
                    </h2>
                    <p className="lead mb-4">
                        Our team is ready to help you design your dream space.
                    </p>
                    <Link
                        to="#"
                        className="btn btn-lg btn-light fw-bold"
                    >
                        Request Samples
                    </Link>
                </div>
            </section>
        </>
    );
};

export default ContactUs;
