// TODO: Crear nuestro input, esto es solo un placeholder para empezar a utilizarlo desde fuera
import React from 'react';
import { Input as RNEInput, InputProps } from '@rneui/themed';

export type HartzInputProps = InputProps;

const Input: React.FC<HartzInputProps> = (props) => {
  return <RNEInput {...props} />;
};

export default Input;