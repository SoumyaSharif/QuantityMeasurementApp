# UC18: Google Authentication and User Management for Quantity Measurement

## Overview
This use case focuses on implementing secure authentication and user management features in the Quantity Measurement application using modern security standards.

## Features
- Google Authentication using OAuth 2.0
- User Registration and Login
- Secure API access using JWT (JSON Web Token)
- Role-based access control (if applicable)

## Technologies Used
- Spring Security
- JWT (JSON Web Token)
- OAuth 2.0 (Google Authentication)

## Description
This module enables users to securely log in using their Google accounts and access application features based on authentication and authorization rules. It integrates OAuth 2.0 for third-party authentication and uses JWT for maintaining secure sessions.

## Key Components
- Authentication Controller
- JWT Utility & Filter
- OAuth2 Login Configuration
- User Service & Repository
- Security Configuration

## Flow
1. User clicks on "Login with Google"
2. Redirect to Google OAuth consent screen
3. User authenticates with Google
4. Application receives user details
5. JWT token is generated
6. User accesses secured endpoints using JWT

## Benefits
- Enhanced security
- Simplified login experience
- Scalable authentication system

## Notes
- Ensure proper configuration of Google OAuth credentials
- Protect JWT secret keys
- Handle token expiration and refresh mechanisms properly
