import Colors from '@/constants/Colors';
import React, { ReactNode } from 'react';
import {
  KeyboardAvoidingView,
  Platform,
  TouchableWithoutFeedback,
  Keyboard,
  StyleSheet,
  ViewStyle,
  useColorScheme,
} from 'react-native';

type KeyboardHiderProps = {
  children: ReactNode;
  style?: ViewStyle;
};

const KeyboardHider: React.FC<KeyboardHiderProps> = ({ children, style }) => {

  const theme = useColorScheme() ?? 'light';
  const bg = Colors[theme]['background'];

  return (
    <KeyboardAvoidingView
      behavior={Platform.OS === 'ios' ? 'padding' : undefined}
      style={[styles.container, { backgroundColor: bg }, style]}
    >
      <TouchableWithoutFeedback onPress={Keyboard.dismiss} accessible={false}>
        {/* Wrapper para cerrar el teclado */}
        {children}
      </TouchableWithoutFeedback>
    </KeyboardAvoidingView>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
  },
});

export default KeyboardHider;
