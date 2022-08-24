import React, { Component } from "react";
import { Link } from "react-router-dom";
import axios from "axios";

export default class ShowCars extends Component {
  state = {
    cars: [],
    owners: [],
    carsAndOwners: [],
    loading: true,
  };

  componentDidMount() {
    let username = localStorage.username;
    if (!username) {
      this.props.history.push(`/`);
    } else {
      axios
        .get("/api/cars")
        .then((res) => {
          this.setState({
            cars: res.data,
          });

          let tempOwners = [];

          this.state.cars.forEach((car) => {
            axios
              .get(`/api/owners/${car.ownerId}`)
              .then((res) => {
                tempOwners.push(res.data);
                this.setState({
                  owners: tempOwners,
                });
                let carsAndOwners = this.state.cars.map((item, i) =>
                  Object.assign({}, item, this.state.owners[i])
                );
                this.setState({
                  carsAndOwners: carsAndOwners,
                  loading: false,
                });
                console.log(carsAndOwners);
                console.log(tempOwners);
              })
              .catch((e) => console.log(e));
          });

          console.log(res.data);
        })
        .catch((e) => {
          console.log(e);
        });
    }
  }

  render() {
    return (
      <>
        <table className="cars-table">
          <thead>
            <tr>
              <th>Car Number</th>
              <th>Owner</th>
              <th>Contact Number</th>
              <th>Parking Lot</th>
              <th>Entry Time</th>
              <th>Exit Time</th>
              <th>Amount Paid</th>
            </tr>
          </thead>
          {this.state.loading ? (
            <p className="loading-msg">Loading...</p>
          ) : (
            <tbody>
              {this.state.carsAndOwners.map((car) => (
                <tr key={car.carId}>
                  <td className="car-num">
                    <Link to={`cars/${car.carId}`}>{car.carNumber}</Link>
                  </td>
                  <td>
                    {car.firstName} {car.lastName}
                  </td>
                  <td>{car.contactNumber}</td>
                  <td>{car.lotNumber}</td>
                  <td>{car.entryDateAndTime}</td>
                  <td>{car.exitDateAndTime}</td>
                  <td>${car.amountPaid}</td>
                </tr>
              ))}
            </tbody>
          )}
        </table>

        {/* {this.state.cars.map((car) => (
          <li>{car.carNumber}</li>
        ))}

        {this.state.owners.map((owner) => (
          <li>{owner.ownerId}</li>
        ))}

        <h3>Smile :) </h3> */}
      </>
    );
  }
}
