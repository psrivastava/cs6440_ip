const { default: Axios } = require("axios");
// https://medium.com/@adostes/using-environment-variables-in-a-react-application-ac3b6c307373

export const fakeAuth = {
    isAuthenticated: (localStorage.getItem("userId") != null),

    authenticate(email, pwd, cb) {

      if (localStorage.getItem("userId") != null) {
        fakeAuth.isAuthenticated = true;
        cb();
        return;
      }

      /*
      const API_HOST = process.env.REACT_APP_API_SERVER || 'http://localhost:8080';
      const url = `${API_HOST}/login`;
      */
      const url = "https://cs6440-drugabuse-api.herokuapp.com/login";
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
      localStorage.clear();
      setTimeout(cb, 100);
    }
  };
