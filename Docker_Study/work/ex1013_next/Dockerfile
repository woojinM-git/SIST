FROM node:apline
WORKDIR /app
COPY pakage*.json ./
RUN npm install
COPY ./ ./
CMD npm run dev