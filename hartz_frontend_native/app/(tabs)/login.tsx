import { StyleSheet } from 'react-native';
import { Image } from '@rneui/themed';
import { Button, Text, View } from '@/components/utils/Themed';
import KeyboardHider from '@/components/utils/KeyboardHider';
import HartzInput from '@/components/HartzInput';

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
          <Text style={styles.title}>Log in to your account</Text>
        </View>

        <View style={styles.form}>
          <HartzInput placeholder='email@domain.com'></HartzInput>
          <HartzInput placeholder='password'></HartzInput>
        </View>

        <View style={{ width: '60%' }}>
          <Button
            variant='primary'
            title='Log in'
            onPress={() => console.log('Login Pressed')}
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
