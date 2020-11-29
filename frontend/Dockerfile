#dockerfile

# pull official base image
FROM node:13.12.0-alpine

RUN mkdir -p /frontend

# set working directory
WORKDIR /frontend

# install git
#RUN apk update
#RUN apk add --no-cache git

# https://gist.github.com/wwerner/05a8e627e8f3ba18300db745511d3bcb
# see https://devcenter.heroku.com/articles/exec#enabling-docker-support
#RUN apk add --no-cache curl bash openssh python
#ADD heroku-exec.sh /app/.profile.d/heroku-exec.sh
#RUN chmod a+x /app/.profile.d/heroku-exec.sh
# ====================================================================

# add `/app/node_modules/.bin` to $PATH
# ENV PATH /frontend/node_modules/.bin:$PATH

# install app dependencies
COPY . .
#COPY frontend/package-lock.json ./frontend
RUN npm ci
RUN npm run build
#RUN npm audit fix --force
#RUN npm install react-scripts@3.4.0 -g --silent
ENV REACT_APP_API_SERVER="https://cs6440-drugabuse-api.herokuapp.com"

# debug script
#COPY busy.sh .

EXPOSE 5000

# start app
CMD ["npm", "run", "deploy"]

# debug
#CMD ["/bin/sh", "busy.sh"]
