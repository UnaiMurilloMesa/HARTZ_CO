import { Button, Text, View, Input } from '@/components/utils/Themed';
import { Keyboard, KeyboardAvoidingView, Platform, StyleSheet, TouchableWithoutFeedback } from 'react-native';

export default function TabOneScreen() {
  return (
    <KeyboardAvoidingView
      behavior={Platform.OS === 'ios' ? 'padding' : undefined}
      style={{ flex: 1 }}
    >
      {/* Para ocultar el tecldo cuando al pulsar en el fondo */}
      <TouchableWithoutFeedback onPress={Keyboard.dismiss}>
        <View style={styles.container}>
          <Text type='title'>Tab One</Text>
          <Button
            variant='primary'
            title="Primary"
            onPress={() => console.log('Pressed')}
          />
          <Button
            variant='secondary'
            title="Secondary"
            onPress={() => console.log('Pressed')}
          />
          <Input placeholder="Escribe algo..." />
        </View>
      </TouchableWithoutFeedback>
    </KeyboardAvoidingView>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    padding: 16,
    gap: 20,
    alignItems: 'center',
    justifyContent: 'center',
  },
});
