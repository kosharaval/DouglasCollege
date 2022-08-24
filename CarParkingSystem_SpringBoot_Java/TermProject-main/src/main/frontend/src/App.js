import "./App.css";
import React from "react";
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";

import MyNavbar from "./components/myNavbar";
import Test from "./components/test";
import AddCar from "./components/addCar";
import ShowCars from "./components/showCars";
import ExitCar from "./components/exitCar";
import Login from "./components/login";

function App() {
  return (
    <Router>
      <div className="container">
        <MyNavbar />
        <Switch>
          <Route path="/" exact component={Login} />
          <Route path="/cars" exact component={ShowCars} />
          <Route path="/cars/:carId" exact component={ExitCar} />
          <Route path="/addCar" exact component={AddCar} />
          {/* <Route component={NotFound} /> */}
        </Switch>
      </div>
    </Router>
  );
}

export default App;
