import './App.css';
import { BrowserView, MobileView } from 'react-device-detect'

const App = () => {
  return (
    <div className="App">
      <BrowserView>
        데스크톱브라우져!
      </BrowserView>
      <MobileView>
        모바일 브라우져!
      </MobileView>
      
    </div>
  );
}

export default App;