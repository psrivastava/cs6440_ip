const { default: Axios } = require("axios");

export const fakeAuth = {
    isAuthenticated: false,
    authenticate(email, pwd, cb) {

      const url = "http://localhost:8080/login";
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
