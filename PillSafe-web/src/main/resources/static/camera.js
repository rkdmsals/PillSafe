async function startCamera() {
    const videoElement = document.getElementById('video');
    const canvasElement = document.getElementById('canvas');

    const constraints = { video: true };
    const stream = await navigator.mediaDevices.getUserMedia(constraints);
    videoElement.srcObject = stream;
}

async function capturePhotoAndDetectText() {
    const videoElement = document.getElementById('video');
    const canvasElement = document.getElementById('canvas');
    const resultElement = document.getElementById('result');

    canvasElement.width = videoElement.videoWidth;
    canvasElement.height = videoElement.videoHeight;
    canvasElement.getContext('2d').drawImage(videoElement, 0, 0);
    const photoData = canvasElement.toDataURL('image/png');

    // photoData를 서버로 전송하여 detectText 함수 호출
    fetch('/detect-text', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded',
        },
        body: 'imageData=' + encodeURIComponent(photoData),
    })
        .then(response => response.text())
        .then(result => {
            // 결과값을 resultElement에 표시
            resultElement.textContent = result;

            result = "타이레놀";

            window.location.href = `/getDrugInfo?textResult=${encodeURIComponent(result)}`;
        })
        .catch(error => {
            console.error('Error:', error);
        });
}

window.onload = function() {
    startCamera(); // 페이지 로드 시 카메라 화면 표시
};