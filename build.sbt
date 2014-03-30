name := "timeout-my-http-requests"

version := "1.0"


version := "1.0-SNAPSHOT"

autoScalaLibrary := false

crossPaths := false

EclipseKeys.projectFlavor := EclipseProjectFlavor.Java

mainClass in (Compile, run) := Some( "FutureAndCallable.Main")

libraryDependencies ++= Seq( 
    "junit" % "junit" % "4.10",
    "com.novocode" % "junit-interface" % "0.10-M1" % "test"
    )