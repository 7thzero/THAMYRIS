# interceptor

This is a quick golang-based application that intercepts and logs traffic sent to it. You can use something like `dnsmasq` to route traffic from `ap.jawalife.net` to a host running this tool to capture payloads that are sent.

As part of a manual analysis, I take those payloads and forward them to the 'real' ap.jawalife.net using [Insomnia](https://insomnia.rest/)


# Note on Payloads
The first 8 characters of a `data` payload are the encryption key. You can use the `Dec_Enc` class in the android project to decode these payloads

Once you unwrap them you'll see that a unique pub/priv keypair is used to further protect certain data sent over the channel. I haven't explored much into this.
