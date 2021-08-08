# THAMYRIS
Companion App for Amiccom Smart IP Camera  -  Network Configuration Tool

Featured in DEF CON 29 Talk [Why does my security camera scream like a Banshee? Signal analysis and reverse engineering an audio communication protocol](https://www.youtube.com/watch?v=JpL3lySZNeM)

Inside the `android` directory you will find the source code for an android application that can be used to pair the wifi camera with your wireless network. It's a pretty straight-forward tool and more or less a 'one trick pony'. Be warned, it produces some ugly sounds!


## Instructions
- Install the app
- Open the app
- Specify your SSID
- Specify your password (currently shows as cleartext, you can change this in the code)
- Turn up volume on your phone to between 50 and 75%
- Set your phone nearby the camera
- Start the camera
- Once you see the green light flash quickly (a couple times per second)...
- Click the button on the app to send the sounds
- Your ears will hear odd sounds for a little bit
- If all is well, the camera will be paired to your wireless network

- At this point the camera is ready for further analysis


# tools directory
When the camera is bootstrapped, it is assigned a random 6 hex-character password (at least I haven't picked up on a pattern yet!). In this directory you will find an RTSP brute-force tool that can be used as a base for brute-forcing the password. The camera is slow and can only take 5 to 30 concurrent requests per second

You will also find

# Further Analysis (software)
The wireless camera is pretty chatty with its cloud masters. To gain better control of the camera, one would need to master and impersonate the cloud server to send your own control codes.

## Setup
I have an old laptop running hostapd and dnsmasq to filter communication from the camera through my test bed. This is important as it lets me route `ap.jawalife.net` traffic to analysis tools

## Current notes
Requests from the **camera** look like this
  https://ap.jawalife.net/jawa/security.do?data=587843996f75fa7dff0cf67dd6ab985318a39bd032b26367e24ec935edf187d0c3f9ebba3dc916be91b5fd2078ac7e834f830b68493a1ea8468cd400e21ca956da24a8e7d32028218ed50546&TPWlanWarnChecked=0

Responses look similar:
   C5C405CB9C64D32C53F109F979498FC889E5AC3A49FC40123E3288AF085714E808AC577AA34396B82ABAD8784BDC062A7A3BA505797BF4CE24D63E48D7A9D8444EB4B229B8C6F59CD5CB18CAD7A9D8444EB4B229DC7EB867CF236E60

The `first 8 characters` of both the request and response payloads are encryption keys that can be used to decode the rest of the payload

### Request decoded
```
pk:YgmDXXrP6rPzE6tQbyDJOqAbIXgm0EUapn1Jej5Tg5wVBT3T/oZ1yRQDhnWJ4ip9zez9p0ud4Z59
N/euWwy8jKvdxyEVjJFt8UG/VGzM9/dSuhloQk4yJnCsrySDD8r6fCXJv5oZ5WHyUM3vJVNoBQ2R
a2GHd9fJryqmVC1EX+X326D4Vh98ziPl5jGuel4XFARn1iG+u1PDN9FmRVWKvpaVh5Y0e35Vsgvp
v10Lk2TfR0OR9RGnXorwFXnABdgaM1O4vOQpNR5EkaL/s9RJ4mQcGH+mth0NxlYxRC5oV4omcRmu
ODqrIjWTVi2WiHw3+HFZGsjoTi7ZxSncXyRVIHWYvpS6ihP2sfMhpwTRTe0=

&cv:3.3.0
&token:0a25896e7f59ae6dd931b796eb8d9a78c7013dbf
&tm:1626055772201
&pv:2.0
&cl:android
```

- cv == version string of the jawa android application listed in the manifest xml
- pk == AES Encrypted public key, from a locally-generated pub/priv keypair
- tm == current time on android phone in milliseconds
- cl == static string 'android' (probably to differentiate between
- pv == a static 'protocol version' string that may be incremented in the future I presume
- token == a mangled token generated in the reversed codebase using a method titled `hex_hmac_sha1`. It includes a special magic hard-coded token you can see in the code


### Reply decoded
```
{
	"serverBean":{
		"dowhat":0,
		"keyValue":"ITHI",
		"status":0
	},
	"status":"OK"
}
```

The reply from the server for this message is simply JSON


### How to Parse
In the android app you will see classes in `com.ithink.util`. One of them is called `Dec_Enc` and it can be used to decode like this:

```
                String capturedInsomnia = "9C64D32C53F109F979498FC889E5AC3A49FC40123E3288AF085714E808AC577AA34396B82ABAD8784BDC062A7A3BA505797BF4CE24D63E48D7A9D8444EB4B229B8C6F59CD5CB18CAD7A9D8444EB4B229DC7EB867CF236E60";
                String randomInsomnia = "C5C405CB";
                String decodedInsomnia = new Dec_Enc().Decode(capturedInsomnia, randomInsomnia);
```


# Further Analysis (hardware)
Pulling the camera apart, it appears to use a **GOKE GK7102**. I tried to use the tricks described in [zsgxh1hacks](https://github.com/ant-thomas/zsgx1hacks), unfortunately it did not seem to work for me.

There might be either a JTAG or a UART port on the board. Either would be great to dig deeper

