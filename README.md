# AppEightExerciseMemo
## AlertDialog
- 맞춤 레이아웃을 표시할 수 있는 대화상자
```
dialog = AlertDialog.Builder(this) // AlertDialog.Builder 객체를 생성
      .setView(R.layout.custom_dialog) // 맞춤 레이아웃
      .setTitle("타이틀바 제거 안할 경우 타이틀 설정")
      .create() // AlertDialog 객체를 생성
      
dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))   // Drawable 지정 : 모서리 둥글게 하기, 배경 색 변경 등 
dialog.window?.requestFeature(Window.FEATURE_NO_TITLE)     // 타이틀바 제거 : .setView() 이후에 작성할 것

dialog.show()
dialog.window?.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT) // 크기 지정 : .show() 가 먼저 나와야 함

dialog.dismiss() // 다이알로그 끄기
```
AppEightExerciseMemo에서 [AlertDialog](https://github.com/okrecords/AppEightExerciseMemo/blob/721c2a4f08bb627e95b4830bfa7bf2b615f9cb62/app/src/main/java/com/okre/appeightexercisememo/MainActivity.kt#L54)
<br/><br/><br/>

## Firebase 익명 로그인
- 익명 계정 생성 및 사용
- Firebase Authentication Sign-in method에서 익명을 선택한 후 [Firebase Documentation](https://firebase.google.com/docs/auth/android/anonymous-auth?hl=ko)을 참고하여 코드 작성

AppEightExerciseMemo에서 [Firebase 익명 로그인](https://github.com/okrecords/AppEightExerciseMemo/blob/master/app/src/main/java/com/okre/appeightexercisememo/SplashActivity.kt)
<br/><br/><br/>

## Firebase Realtime Database
- Firebase Realtime Database [Firebase Documentation](https://firebase.google.com/docs/database/android/start?hl=ko)을 참고하여 코드 작성
- push() : 고유 키를 생성하여 저장, push()를 사용하지 않을 경우 같은 데이터는 덮어쓰기

AppEightExerciseMemo에서 [Firebase Realtime Database](https://github.com/okrecords/AppEightExerciseMemo/blob/master/app/src/main/java/com/okre/appeightexercisememo/MainActivity.kt)

