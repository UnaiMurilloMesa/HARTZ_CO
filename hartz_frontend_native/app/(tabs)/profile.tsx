import { StyleSheet } from 'react-native';
import { Image, Icon } from '@rneui/themed';
import { Button, Text, View, Divider, useThemeColor, ScrollView } from '@/components/utils/Themed';
import { PrivateProfile } from '@/interfaces';
import HartzHeatMap from '@/components/HartzHeatMap';

const profile: PrivateProfile = {
  username: 'Alonso',
  email: 'alonso@gmail.com',
  mascot: 'panda',
  biography: 'Soy Alonso :)',
  height: 180,
  weight: 70,
  birthDate: new Date('2004-07-24T00:00:00')
};

const images = {
  anteater: require('../../assets/images/bears/anteater.png'),
  black: require('../../assets/images/bears/black.png'),
  grizzly: require('../../assets/images/bears/grizzly.png'),
  panda: require('../../assets/images/bears/panda.png'),
  polar: require('../../assets/images/bears/polar.png'),
  tanuki: require('../../assets/images/bears/tanuki.png'),
};

const data = [
  0.12, 0.45, 0.34, 0, 0.87, 0, 0.56, 0, 0.29, 0, 0.91, 0.73, 0.67, 0,
  0.35, 0.44, 0.58, 0, 0.13, 0.21, 0.79, 0, 0.6, 0, 0, 0.88,
  0.01, 0.18, 0.3, 0.92, 0, 0.53, 0.72, 0.84, 0.66, 0.37, 0,
  0.95, 0, 0.04, 0.2, 0, 0.49, 0.62, 0, 0.77, 0.99, 0.25, 0.15, 0.1, 0
];

function getAge(birthDate: Date): number {
  const today = new Date();
  let age = today.getFullYear() - birthDate.getFullYear();
  const monthDiff = today.getMonth() - birthDate.getMonth();
  if (monthDiff < 0 || (monthDiff === 0 && today.getDate() < birthDate.getDate())) {
    age--;
  }
  return age;
}

export default function Profile() {
  return (
    <ScrollView contentContainerStyle={styles.container}>
      <View style={styles.header}>
        <View style={styles.header_content}>
          <Button title='' variant='secondary' >
            <Icon
              name='edit'
              color={useThemeColor({}, 'primary')}
            />
          </Button>
          <Text style={styles.title}>Profile</Text>
          <Button title='settings' variant='secondary'>
            <Icon
              name='settings'
              color={useThemeColor({}, 'primary')}
            />
          </Button>
        </View>
        <Divider />
      </View>

      <View style={styles.info}>
        <View style={styles.picture}>
          <Image
            source={require('../../assets/images/profile_picture_placeholder.jpg')}
            style={styles.image}
          />
        </View>
        <View style={styles.bacic_info}>
          <Text style={styles.username}>{profile.username}</Text>
          <Divider />
          <Image
            source={images[profile.mascot]}
            style={styles.image}
          />
        </View>
      </View>

      <View style={styles.workload}>
        <Text style={styles.subtitle}>Workload</Text>
        <HartzHeatMap style={styles.heatmap} data={data} columns={13} cellSize={15} color={useThemeColor({}, 'primary')} />
      </View>

      <View style={styles.personal_data}>
        <Text style={styles.subtitle}>Personal data</Text>
        <View style={styles.data_container}>
          <View style={styles.data_row}>
            <Text style={styles.data_label}>Weight</Text>
            <Text style={styles.data_value}>{profile.weight} kg</Text>
          </View>
          <View style={styles.data_row}>
            <Text style={styles.data_label}>Height</Text>
            <Text style={styles.data_value}>{profile.height} cm</Text>
          </View>
          <View style={styles.data_row}>
            <Text style={styles.data_label}>Age</Text>
            <Text style={styles.data_value}>{getAge(profile.birthDate)} y/o</Text>
          </View>
        </View>
      </View>

    </ScrollView>
  );
}

const styles = StyleSheet.create({
  container: {
    paddingVertical: 50,
    alignItems: 'center',
    gap: 50,
  },
  header: {
    display: 'flex',
    flexDirection: 'column',
    justifyContent: 'center',
    alignItems: 'center',
    width: '100%'
  },
  header_content: {
    width: '100%',
    display: 'flex',
    flexDirection: 'row',
    alignItems: 'center',
    justifyContent: 'space-evenly',
    marginBottom: 15
  },
  title: {
    fontSize: 20,
    fontWeight: 'bold',
  },
  subtitle: {
    alignSelf: 'flex-start',
    width: '100%',
    paddingLeft: 15,
    fontSize: 25,
    fontWeight: 'bold',
    textAlign: 'left'
  },
  info: {
    display: 'flex',
    flexDirection: 'row',
    width: '100%'
  },
  picture: {
    display: 'flex',
    justifyContent: 'center',
    alignItems: 'center',
    width: '50%'
  },
  image: {
    width: 100,
    height: 100,
    resizeMode: 'contain',
    borderRadius: '50%'
  },
  bacic_info: {
    display: 'flex',
    flexDirection: 'column',
    width: '50%',
    justifyContent: 'center',
    alignItems: 'center'
  },
  username: {
    fontSize: 25,
    marginBottom: 10
  },
  workload: {
    alignItems: 'center',
    width: '100%',
  },
  heatmap: {
    marginVertical: 15
  },
  personal_data: {
    width: '100%'
  },
  data_container: {
    width: '80%',
    alignSelf: 'center',
    borderRadius: 20,
    paddingVertical: 15,
    gap: 10,
  },
  data_row: {
    paddingHorizontal: 10,
    paddingVertical: 5,
    borderRadius: '2%',
    backgroundColor: 'rgba(0,0,0,0.1)',
    flexDirection: 'row',
    justifyContent: 'space-between',
  },
  data_label: {
    fontSize: 16,
    fontWeight: '500',
  },
  data_value: {
    fontSize: 16,
    fontWeight: 'bold',
  },
});
