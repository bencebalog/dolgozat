Program Hullam;
   Uses Crt;
    Var X,Y,S,N,R:Integer;

Begin
  TextMode(CO80+Font8x8); Randomize; X:=1;
  Repeat
    TextColor(LightGreen);
    If X=1 Then Begin N:=Random(5)+1; R:=Random(15)+7; End;
    For Y:=1 to 50 Do Begin GotoXY(X,Y); Write(' '); End;
    S:=25+Trunc(R*Sin(360/80*X*N*3.14/180)); Y:=S;
    If S>25 Then For Y:=25 to S Do Begin GotoXY(X,Y); Write('E'); End;
    If S<25 Then For Y:=25 Downto S Do Begin GotoXY(X,Y); Write('E'); End;
    TextColor(LightRed); GotoXY(X,Y); Write('*');
    Sound(800-Y*10); Delay(50);
    TextColor(Yellow); GotoXY(X,Y); Write('O');
    X:=X+1; If X=80 Then X:=1;
  Until KeyPressed;
  NoSound;
End.