import React from 'react';

function Camera(){
    return (
    <div className="camera">
        <input type="file" id="camera" name="camera" capture="camera" accept="image/*" />
        <br />

        <img id="pic" style="width:100%;" />
    </div>
    );
}

export default Camera;