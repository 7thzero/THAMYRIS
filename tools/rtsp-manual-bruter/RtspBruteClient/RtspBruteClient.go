package RtspBruteClient

import (
	"log"
	"net/http"
	"net/url"
)

type RtspBruteClient struct {
	username string
	password string
	nonce	 string
	realm	 string
	target	 string
	targetUrl string
}

func ( rbc *RtspBruteClient) SetTarget(targetUrl string){
	uParsed, uParsedErr := url.Parse(targetUrl)
	if uParsedErr != nil{
		log.Println("Error while parsing URL: ", targetUrl)
		log.Println(uParsedErr)
	}

	rbc.username = uParsed.User.Username()
	rbc.password, _ = uParsed.User.Password()
	rbc.target = uParsed.Hostname()+":"+uParsed.Port()+uParsed.RequestURI()
	rbc.targetUrl = "rtsp://"+rbc.target
	// Add the URI??
}

//
// Returns an error if authentication was unsuccessful
func (rbc *RtspBruteClient) Authenticate(password string) (error){
	hdr := make(http.Header)
	hdr.Add("CSeq", "2")
	hdr.Add("User-Agent", "LibVLC/3.0.16 (555 edition)")

	u, uErr := url.Parse(rbc.targetUrl)
	log.Println("uErr:", uErr)
	hr := &http.Request{
		Method:           "OPTIONS",
		URL:              u,
		Proto:            "RTSP",
		ProtoMajor:       1,
		ProtoMinor:       0,
		Header:           hdr,
		ContentLength:    0,
	}

	c := http.Client{}
	r, rErr := c.Do(hr)
	log.Println(rErr)
	if r != nil {
		log.Println(r)
	}
	return nil
}