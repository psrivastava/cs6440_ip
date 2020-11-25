#dockerfile

# pull official base image
FROM node:13.12.0-alpine

RUN mkdir -p /frontend

# set working directory
WORKDIR /frontend

# add `/app/node_modules/.bin` to $PATH
ENV PATH /frontend/node_modules/.bin:$PATH

# install app dependencies
COPY frontend .
#COPY frontend/package-lock.json ./frontend
RUN npm install --silent
#RUN npm audit fix --force
#RUN npm install react-scripts@3.4.0 -g --silent

# debug script
COPY busy.sh .

EXPOSE 3000

# start app
CMD ["npm", "run", "deploy"]

# debug
#CMD ["/bin/sh", "busy.sh"]