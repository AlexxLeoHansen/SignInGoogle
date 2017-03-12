# SignInGoogle
Sign in with Google. Collect your account's email and name. Sign out and revoke credentials.

This is a brief and simple example of how to sign in with a Google account, get our email and name and sign out revoking credentials.
That means, next time we try to sign in, we must grant permissions again.

There are few additional steps we should take before start programming:
0. 
1. Go to: https://developers.google.com/identity/sign-in/android/start-integrating and click on: Get a configuration file
You should write your app name, package name and Android Signing Certificate SHA-1. I reckon everything is pretty good explained in the prior
link. 
2.Get your google-services.json file that should be added to /app/ folder.
3. Add Google Service Plugin and Services to your gradle dependencies as the prior link explain in order to get buttons and all
additional functions in your code.
4. Done from the point of files, apis and those boring but relevant stuff. Start coding!

![signingoogle](https://cloud.githubusercontent.com/assets/19878151/23831248/daac31d0-071d-11e7-8b0f-92677a5ced64.png)
![accountsignout](https://cloud.githubusercontent.com/assets/19878151/23831247/da933f18-071d-11e7-9567-65bcef71ce7d.png)
