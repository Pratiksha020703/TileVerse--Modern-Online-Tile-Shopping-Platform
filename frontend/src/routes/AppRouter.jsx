// import { Routes, Route } from "react-router-dom";
// import App from "../layouts/App";
// import AuthLogin from "../layouts/AuthLogin";

// import Home from "../pages/Home";
// import Products from "../pages/Products";
// import Gallery from "../pages/Gallery";
// import Account from "../pages/Account";
// import Login from "../components/Login";
// import Contact from "../pages/ContactUs";
// import LoginExtra from "../components/LoginExtra";

// const AppRoutes = () => {
//     return (
//         <Routes>
//             {/* Layout Route */}
//             <Route element={<App />}>
//                 <Route path="/" element={<Home />} />
//                 <Route path="/products" element={<Products />} />
//                 <Route path="/gallery" element={<Gallery />} />
//                 <Route path="/account" element={<Account />} />
//                 <Route path="/contactus" element={<Contact />} />
//             </Route>
//             <Route element={<AuthLogin />}>
//                 <Route path="/signin" element={<LoginExtra />} />
//                 <Route path="/signup" element={<Login />} />
//             </Route>
//         </Routes>
//     );
// };

// export default AppRoutes;

import { Routes, Route } from "react-router-dom";
import App from "../layouts/App";
import AuthLogin from "../layouts/AuthLogin";
import Admin from "../layouts/Admin";
import PrivateRoute from "./PrivateRoute";
import AdminRoute from "./AdminRoute";


import Home from "../pages/Home";
import Products from "../pages/Products";
import Gallery from "../pages/Gallery";
import Account from "../pages/Account";
import Contact from "../pages/ContactUs";
import Cart from "../pages/Cart";
import ProductDetails from "../pages/ProductDetails";

//import ProductDetails from "../pages/ProductDetails";
import Payment from "../pages/Payment";
import Wishlist from "../pages/Wishlist";
import ManageAddress from "../pages/ManageAddress";
import CheckoutAddress from "../pages/CheckoutAddress";
import EditProfile from "../pages/EditProfile";
import OrderPlaced from "../pages/OrderPlaced";
import ViewOrders from "../pages/ViewOrders";
import OrderDetails from "../pages/OrderDetails";


import AdminDashboard from "../pages/AdminDashboard";
import AdminProducts from "../pages/admin/AdminProducts";
import AdminInventory from "../pages/admin/AdminInventory";




import Login from "../components/Login";
import ForgotPassword from "../components/ForgotPassword";
import ResetPassword from "../components/ResetPassword";


import { CartProvider } from "../context/CartContext";

const AppRoutes = () => {
  return (
    <CartProvider>
      <Routes>

        {/* AUTH ROUTES */}
        <Route element={<AuthLogin/>}>
          <Route path="/auth" element={<Login />} />
          <Route path="/forgot-password" element={<ForgotPassword />} />
          <Route path="/reset-password/:token" element={<ResetPassword />} />
        </Route>

        {/* USER APP ROUTES */}
        <Route element={<App />}>
          <Route path="/" element={<Home />} />
          <Route path="/products" element={<Products />} />
          <Route path="/gallery" element={<Gallery />} />
          <Route path="/contactus" element={<Contact />} />
          {/* <Route path="/productdetails/:id" element={<ProductDetails />} /> */}
          
         <Route path="/productdetails/:id" element={<ProductDetails />} />
          


          {/* PROTECTED ROUTES */}
          <Route
            path="/account"
            element={
              <PrivateRoute>
                <Account />
              </PrivateRoute>
            }
          />
          <Route
            path="/cart"
            element={
              <PrivateRoute>
                <Cart />
              </PrivateRoute>
            }
          />
          <Route
            path="/payment"
            element={
              <PrivateRoute>
                <Payment />
              </PrivateRoute>
            }
          />

          <Route 
            path="/order-success" 
            element={
              <PrivateRoute>
            <OrderPlaced />
            </PrivateRoute>
            } />

            <Route
              path="/vieworders"
              element={
                <PrivateRoute>
                  <ViewOrders />
                </PrivateRoute>
              }
            />

            <Route
              path="/order/:orderId"
              element={
                <PrivateRoute>
                  <OrderDetails />
                </PrivateRoute>
              }
            />




          <Route
            path="/wishlist"
            element={
              <PrivateRoute>
                <Wishlist />
              </PrivateRoute>
            }
          />

          <Route
            path="/address"
            element={
              <PrivateRoute>
                <ManageAddress />
              </PrivateRoute>
            }
          />

          <Route path="/checkout/address"
           element={
            <PrivateRoute>
              <CheckoutAddress />
           </PrivateRoute>
          }
           />

          <Route
            path="/editprofile"
            element={
              <PrivateRoute>
                <EditProfile />
              </PrivateRoute>
            }
          />
        </Route>

        {/* ADMIN ROUTES */}
       {/* <Route 
          path="/terms"
          element={
            <AdminRoute>
              <Admin />
            </AdminRoute>
          }
        >
          <Route path="addproducts" element={<AdminProducts />} />
          <Route path="inventory" element={<AdminInventory />} />
          <Route path="admin" element={<AdminDashboard />} />


        </Route> */}
        <Route 
  path="/admin"
  element={
    <AdminRoute>
      <AdminDashboard />
    </AdminRoute>
  }
/>



      </Routes>
    </CartProvider>
  );
};

export default AppRoutes;

