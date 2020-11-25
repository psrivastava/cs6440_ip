import { Route, Switch, Redirect } from 'react-router-dom';
import React from 'react';
import { fakeAuth } from './auth';

function PrivateRoute({ children, ...rest }) {
    return (
      <Route
        {...rest}
        render={({ location }) =>
          fakeAuth.isAuthenticated ? (
            children
          ) : (
            <Redirect
              to={{
                pathname: "/login",
                state: { from: location }
              }}
            />
          )
        }
      />
    );
}

  export default PrivateRoute;