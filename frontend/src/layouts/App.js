import { Outlet } from "react-router-dom";
import Header from "../components/Header";
import Footer from "../components/Footer";
import { Toaster } from "react-hot-toast";   // ✅ add

function App() {
  return (
    <>
      <Toaster position="top-right" reverseOrder={false} />  {/* ✅ popup system */}

      <Header />

      <main>
        <Outlet />
      </main>

      <Footer />
    </>
  );
}

export default App;
