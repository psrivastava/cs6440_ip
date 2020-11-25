const { default: Axios } = require("axios");
// https://medium.com/@adostes/using-environment-variables-in-a-react-application-ac3b6c307373

const { REACT_APP_API_SERVER } = process.env;

export const fakeAuth = {
    isAuthenticated: false,
    authenticate(email, pwd, cb) {

      const url = (typeof REACT_APP_API_SERVER == "undefined")?"http://localhost:8080/login":
          `${REACT_APP_API_SERVER}/login`
      const auth = {
        username: email,
        password: pwd
      };

      Axios.get(url, { auth })
      .then(response => {
        console.log(response);

        fakeAuth.isAuthenticated = true;
        console.log("authenticated !!");
        localStorage.setItem("patientId", "1200");
        localStorage.setItem("userId", "1");

        cb();
      })
      .catch(err => console.log(err))

      //setTimeout(cb, 100); // fake async
    },

    signout(cb) {
      fakeAuth.isAuthenticated = false;
      setTimeout(cb, 100);
    }
  };
