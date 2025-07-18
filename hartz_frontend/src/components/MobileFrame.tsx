import React from 'react';
import './MobileFrame.css';

interface Props {
  children: React.ReactNode;
}

const MobileFrame: React.FC<Props> = ({ children }) => {
  return (
    <div className="mobile-frame-wrapper">
      <div className="mobile-frame">
        <div className="notch"></div>
        <div className="screen-content">{children}</div>
      </div>
    </div>
  );
};

export default MobileFrame;