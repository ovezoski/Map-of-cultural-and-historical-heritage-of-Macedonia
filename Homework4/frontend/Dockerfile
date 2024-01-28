FROM node:20-alpine as builder

WORKDIR /app

COPY package*.json ./

RUN npm install -g npm@latest

COPY . .

RUN npm run build

FROM nginx:alpine

COPY --from=builder /app/out/* /usr/share/nginx/html/
COPY /nginx.conf /etc/nginx/conf.d/default.conf

CMD ["nginx", "-g", "daemon off;"]

EXPOSE 3000

CMD ["npm", "start"]
