import CameraRoll from "@react-native-community/cameraroll";
import React, { useState } from "react";
import { Image, PermissionsAndroid, StyleSheet, Text, TouchableOpacity, View } from "react-native";
import { RNCamera } from "react-native-camera";

export default function App(){
  const cameraRef=React.useRef(null)
  const[picture,setPicture] = useState("이미지경로")


  //권한처리

  const takePhoto = async()=>{

    await PermissionsAndroid.requestMultiple([
      PermissionsAndroid.PERMISSIONS.CAMERA,
      PermissionsAndroid.PERMISSIONS.WRITE_EXTERNAL_STORAGE,
      PermissionsAndroid.PERMISSIONS.READ_EXTERNAL_STORAGE
    ]).then((result)=>{
      if (result['android.permission.CAMERA']
          && result['android.permission.WRITE_EXTERNAL_STORAGE']
          && result['android.permission.READ_EXTERNAL_STORAGE'] === 'granted') {
          console.log("모든 권한 획득");
        } else {
          console.log("권한거절");
        }
    })

    if(cameraRef){
      const data= await cameraRef.current.takePictureAsync({
        quality:1,
        exif:true
      })
      console.log('data',data.uri)


      //사진저장
      if(data){
       const result = CameraRoll.save(data.uri).then(out=>{
         console.log(out)
         setPicture(data.uri)
      }).catch(err=>{
        console.log(`${err.message}`)
      })
      console.log('result',result)
    }

  }
}

  return(
    <View style={styles.container}>
     <RNCamera
     ref={cameraRef}
     style={{width:400, height:500}}
     type={RNCamera.Constants.Type.back}
     captureAudio={false}/>

     <TouchableOpacity onPress={takePhoto}>
      <View style={styles.button}>
      <Text>사진</Text>
      </View>
     </TouchableOpacity>

     <Image 
      style={styles.tinyLogo}
      source={{
        uri:`${picture}`
      }}/>
    </View>
  )
}

const styles = StyleSheet.create({
  container:{
    flex:1,
    justifyContent:"center",
    alignItems:"center"
  },
    button:{
      width:100,
      height:100,
      borderRadius:50,
      borderWidth:5,
      borderColor:"blue",
      borderStyle:"solid",
      backgroundColor:"pink",
      marginTop:30,
      justifyContent:"center",
      alignItems:"center"
    },
    tinyLogo:{
      width:100,
      height:100,
      marginTop:30
    }


})