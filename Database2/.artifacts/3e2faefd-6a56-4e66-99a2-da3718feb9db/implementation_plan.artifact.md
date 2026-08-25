# Implementation Plan - Continue with Google

This plan outlines the steps to integrate Google Sign-In using the modern Android **Credential Manager API** and **Firebase Authentication**.

## User Review Required

> [!IMPORTANT]
> **Web Client ID Required**: You must provide your backend server's OAuth 2.0 Client ID (Web Application type) from the [Google Cloud Console](https://console.cloud.google.com/apis/credentials) or [Firebase Console](https://console.firebase.google.com/).
>
> **Firebase Setup**: Ensure that "Google" is enabled as a Sign-in provider in the Firebase Authentication console.

## Proposed Changes

### Configuration & Dependencies

#### [MODIFY] [libs.versions.toml](file:///C:/Users/cc/Desktop/New%20folder/Database2/gradle/libs.versions.toml)
- Add versions for Firebase BoM, Credentials, and GoogleID.
- Define library entries for `firebase-auth`, `androidx-credentials`, `androidx-credentials-play-services-auth`, and `googleid`.

#### [MODIFY] [build.gradle.kts](file:///C:/Users/cc/Desktop/New%20folder/Database2/app/build.gradle.kts)
- Add the new dependencies to the `dependencies` block.

---

### UI Changes

#### [MODIFY] [activity_sign_in.xml](file:///C:/Users/cc/Desktop/New%20folder/Database2/app/src/main/res/layout/activity_sign_in.xml)
- Add a "Continue with Google" button to the layout.

---

### Implementation Logic

#### [MODIFY] [SignInActivity.kt](file:///C:/Users/cc/Desktop/New%20folder/Database2/app/src/main/java/com/example/database/SignInActivity.kt)
- Initialize `CredentialManager` and `FirebaseAuth`.
- Implement `signInWithGoogle()` to trigger the Credential Manager bottom sheet.
- Implement `handleSignIn()` to process the Google ID Token.
- Implement `firebaseAuthWithGoogle()` to authenticate with Firebase using the Google token.
- Navigate to `WelcomeActivity` upon successful authentication.

---

## Verification Plan

### Automated Tests
- Build the project to ensure all new dependencies are resolved.

### Manual Verification
1. Launch the app.
2. Navigate to the Sign In screen.
3. Tap "Continue with Google".
4. Verify the Google Account selection bottom sheet appears.
5. Select an account and verify successful transition to the Welcome screen with your Google profile details.
