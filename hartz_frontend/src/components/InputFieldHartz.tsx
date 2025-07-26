import React, { type InputHTMLAttributes } from 'react';

interface InputWrapperProps extends InputHTMLAttributes<HTMLInputElement> {}

const InputWrapper: React.FC<InputWrapperProps> = (props) => {
  return (
    <>
      <style>{`
        .custom-input {
          width: 100%;
          padding: 8px 12px;
          background-color: white;
          color: black;
          font-style: italic;
          border: none;
          border-bottom: 2px solid transparent;
          box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
          transition: all 0.3s ease;
        }

        .custom-input::placeholder {
          color: gray;
          font-style: italic;
        }

        .custom-input:focus {
          border-bottom: 2px solid black;
          border-radius: 6px;
          outline: none;
        }
      `}</style>

      <input {...props} />
    </>
  );
};

export default InputWrapper;
