# Fix Errors in ApiInterface and Configure Retrofit

The goal is to resolve the "Unresolved reference" errors in `ApiInterface.kt` by correctly configuring Retrofit dependencies and adding the required annotations.

## Proposed Changes

### Build Configuration

#### [MODIFY] [libs.versions.toml](file:///C:/Users/cc/Desktop/New folder/API/gradle/libs.versions.toml)
- Add stable versions for Retrofit and Gson.
- Define library entries for `retrofit`, `converter-gson`, and `gson`.

#### [MODIFY] [build.gradle.kts](file:///C:/Users/cc/Desktop/New folder/API/app/build.gradle.kts)
- Replace hardcoded snapshot dependencies with version catalog references.

### Source Code

#### [MODIFY] [ApiInterface.kt](file:///C:/Users/cc/Desktop/New folder/API/app/src/main/java/com/example/api/ApiInterface.kt)
- Add `@GET("products")` annotation to `getProductData()`.
- Import `retrofit2.http.GET`.

## Verification Plan

### Automated Tests
- Run `./gradlew :app:assembleDebug` to ensure the project compiles and dependencies are resolved.

### Manual Verification
- Verify that `ApiInterface.kt` no longer shows syntax errors in the editor.
