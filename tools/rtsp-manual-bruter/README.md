# rtsp-manual-bruter
This tool can be used to spin up multiple concurrent sessions to run through all 6-character lower-case hex passwords that could be used by the camera.

We do this to try and guess the password without using the cloud-enabled vendor app. Further research is needed to try and make this quicker and more seamless.

The camera requires that you send an RTSP **OPTIONS** request before you can try digest authentication using a **DESCRIBE** command. Other tools like cameradar unfortunately don't do this, so I had to through together a POC to try and center my thoughts

I've left behind failed attempts in the code so you can see what did/didn't work for educational purposes
