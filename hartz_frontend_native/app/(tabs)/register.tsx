import { StyleSheet } from 'react-native';
import { Image, Input } from '@rneui/themed';
import { Button, Text, View } from '@/components/utils/Themed';
import KeyboardHider from '@/components/utils/KeyboardHider';

export default function InitScreen() {
  return (
    <KeyboardHider>
      <View style={styles.container}>
        <View style={styles.header}>
          <Text style={styles.title}>HARTZ</Text>
          <Image
            source={require('../../assets/images/bears/hartz.png')}
            style={styles.image}
          />
          <Text style={styles.title}>Create your account</Text>
        </View>

        <View style={styles.form}>
          <Input placeholder='username'></Input>
          <Input placeholder='email@domain.com'></Input>
          <Input placeholder='password'></Input>
          <Input placeholder='confirm password'></Input>
        </View>

        <View style={{ width: '60%' }}>
          <Button
            variant='primary'
            title='Continue'
            onPress={() => console.log('Register Pressed')}
          />
        </View>
      </View>
    </KeyboardHider>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
  },
  header: {
    alignItems: 'center',
    marginBottom: 20,
  },
  title: {
    fontSize: 20,
    fontWeight: 'bold',
  },
  form: {
    width: '80%',
    paddingHorizontal: 20,
    marginTop: 20,
    gap: 10,
  },
  image: {
    width: 200,
    height: 200,
    resizeMode: 'contain',
  },
});
