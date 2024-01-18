# Use a base image with Node.js installed
FROM node:20-alpine

# Set the working directory
WORKDIR /app

# Copy package.json and package-lock.json
COPY package*.json ./

# Install dependencies
RUN npm install

# Copy the React app files
COPY . .

# Build the React app
RUN npm run build

# Expose the port your React app will run on
EXPOSE 3000

# Command to run the React app
CMD ["npm", "start"]
