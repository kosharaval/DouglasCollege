import React, { Component } from "react";
import { Link } from "react-router-dom";

class MyNavbar extends Component {
  logout() {
    localStorage.removeItem("username");
    window.location = "/";
  }

  render() {
    return (
      <nav>
        <ul>
          <li>Car Parking System</li>
        </ul>
        <ul>
          <li>
            <Link to="/addCar">Park a car</Link>
          </li>
          <li>
            <Link to="/cars">Show cars</Link>
          </li>
          <li className="logout-btn" onClick={this.logout}>
            Logout
          </li>
        </ul>
      </nav>
    );
  }
}

export default MyNavbar;
