import React, { Component } from "react";
import axios from "axios";

export default class ExitCar extends Component {
  state = {
    owner: {},
    car: {},
    totalTime: 0,
    amount: 0,
    parkingRate: 0,
    exited: false,
    loading: true,
  };

  clickHandler = this.clickHandler.bind(this);

  componentDidMount() {
    axios
      .get(`/api/cars/${this.props.match.params.carId}`)
      .then((res) => {
        let parkingRate = 0;

        if (res.data.carSize === "Small") {
          parkingRate = 3.45;
        } else if (res.data.carSize === "Medium") {
          parkingRate = 4.55;
        } else if (res.data.carSize === "Large") {
          parkingRate = 5.65;
        }

        if (res.data.amountPaid !== 0) {
          this.setState({
            exited: true,
          });
        }

        this.setState({
          parkingRate: parkingRate,
          car: res.data,
          loading: false,
        });

        console.log(res);
      })
      .catch((e) => {
        console.log(e);
      });

    axios
      .get(`/api/owners/${this.props.match.params.carId}`)
      .then((res) => {
        this.setState({
          owner: res.data,
        });

        console.log(res);
      })
      .catch((e) => {
        console.log(e);
      });
  }

  clickHandler() {
    let entryTimeString = this.state.car.entryDateAndTime;
    let exitTimeString = new Date().toLocaleString();
    //console.log(exitTimeString);

    let entryTime = new Date(entryTimeString);
    let exitTime = new Date(exitTimeString);

    let parkingRate = this.state.parkingRate;

    let totalTime = Math.floor(
      (exitTime.getTime() - entryTime.getTime()) / 1000 / 60
    );

    let amount = totalTime * parkingRate;

    axios
      .put("/api/cars", {
        carId: parseInt(this.state.car.carId),
        carNumber: this.state.car.carNumber,
        ownerId: this.state.car.ownerId,
        lotNumber: this.state.car.lotNumber,
        carSize: this.state.car.carSize,
        entryDateAndTime: this.state.car.entryDateAndTime,
        exitDateAndTime: exitTimeString,
        amountPaid: amount,
        totalTime: totalTime,
      })
      .then((res) => {
        console.log(res);
        // this.setState({
        //   totalTime: totalTime,
        //   amount: totalTime * parkingRate,
        // });
        this.setState({
          car: res.data,
        });

        axios
          .put("/api/lots", {
            lotNumber: this.state.car.lotNumber,
            size: this.state.car.carSize,
            empty: true,
          })
          .then((res) => {
            this.setState({
              exited: true,
            });
            console.log(res);
          })
          .catch((e) => console.log(e));
      })
      .catch((e) => console.log(e));
  }

  render() {
    return (
      <>
        {this.state.loading ? (
          <p className="loading-msg">Loading...</p>
        ) : (
          <div className="car-container">
            <h2>Car Details</h2>
            <p>
              <b>Car Number</b> - {this.state.car.carNumber}
            </p>
            <p>
              <b>Car size</b> - {this.state.car.carSize}
            </p>
            <p>
              <b>Owner's Name</b> - {this.state.owner.firstName}{" "}
              {this.state.owner.lastName}
            </p>
            <p>
              <b>Owner's contact number</b> - {this.state.owner.contactNumber}
            </p>
            <p>
              <b>Parking lot number</b> - {this.state.car.lotNumber}
            </p>
            <p>
              <b>Entered at</b> - {this.state.car.entryDateAndTime}
            </p>
            <p>
              <b>Parking rate per minute</b> - ${this.state.parkingRate}
            </p>

            {!this.state.exited ? (
              <button className="exit-btn" onClick={this.clickHandler}>
                Exit Car
              </button>
            ) : (
              <div className="car-amount-time">
                <p className="success-msg">Car has exited!</p>

                <p>
                  <b>Total time</b> - {this.state.car.totalTime} minutes
                </p>
                <p>
                  <b>Total parking cost</b> - ${this.state.car.amountPaid}
                </p>
              </div>
            )}
          </div>
        )}
      </>
    );
  }
}
