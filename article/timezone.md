## 時區

* 在互聯網時代，產品使用者可能會來自世界各地，他們身處不同時區。
* 即使你的目標客戶只屬於一個地區，但這些客戶可能會因為旅行公幹，而走到另一個時區。
* 個別地區可能會因為政治等因素，而改變時區。

### 實例

#### Aniplex《きららファンタジア》

Aniplex 在 2017 年 12 月 11 日，於 iOS 及 Android 平台推出手機遊戲軟件[《きららファンタジア》](https://kirarafantasia.com/)。發行地區只限日本，所以只有日本玩家可以從日本的 App Store / Google Play。至於日本以外的玩家，iOS 的話可以用假日本賬號下載，而 Android 則要用其他不正常渠通下載。

![きららファンタジア](../image/kirarafantasia_cover.jpg)

玩家只要每日打開遊戲，就會獲贈一些每日登入獎勵。

![きららファンタジア login bonus](../image/kirarafantasia_loginbonus.jpg)

然而，在日本 UTC+9 以外的玩家，例如香港和中國 UTC+8 的玩家，卻可以在一天得到登入禮物兩次。經玩家測試後，懷疑是時區不同導致遊戲出錯。伺服器在日本時間 00:00 先派了一份禮物。然後在中國/香港時間 00:00，用戶端再向伺服器拿多一份禮物。（另見 [不要相信用戶端](dont_trust_client.md) ）

雖然這個問題是日本時區以外才發生，但這個漏洞也被日本玩家利用。

#### 任天堂《Splatoon 2》

任天堂在 2017 年 7 月 21 日，於旗下的 Switch 主機推出遊戲軟件[《Splatoon 2》](https://splatoon.nintendo.com/)。發行地區包括美國，歐洲，日本和台灣。

![Splatoon 2](../image/splatoon_cover.jpg)

遊戲中定期會舉辦一些活動，亦有活動時間表。由於遊戲的對象是全世界的玩家，所以時間表的顯示也做過時區處理。

以下是系統設定為 UTC+8 時顯示的時間表。

![Splatoon 2 time table tz8](../image/splatoon_tz8.jpg)

而當系統設定為 UTC+5 時，顯示的日期時間亦會隨之改變。

![Splatoon 2 time table tz5](../image/splatoon_tz5.jpg)

玩家無論身處甚麼時區，都可以看到根據自己時區顯示的時間表，亦可以正常參與活動，不會遇到任何問題。

### 注意及建議

* 時區差別未必是以小時計。尼泊爾的時區是 UTC+9:45。
* 某些地區有[日光節約時間](https://zh.wikipedia.org/wiki/%E5%A4%8F%E6%97%B6%E5%88%B6) (Daylight saving time) ，在夏季時間會調快一小時。
* 儘可能使用坊間既有的程式庫做時間計算，而且要定期更新。例如，現在沒有多少人會知道 1989 年中國是行日光節約時間。現有程式庫通常都會處理這些麻煩。
* 如果服務對象不是單一時區。營運公告中有提及到時間的地方，應該要註明時區。
* 如果有些資料與時間掛勾，例如課金遊戲的體力恢復，或者登入獎勵，或者活動的舉行時間，就應該以 64 bit 的 epoch time 做時間判定。（另見 [與時間掛勾的資料](time_related_data.md) ）

### 編程

#### Java

[code](https://raw.githubusercontent.com/luzi82/devcommonsense/master/example/timezone/java/Example.java)

```
ZoneId chinaZone = ZoneId.of("Asia/Shanghai");
ZoneId gmtZone = ZoneId.of("GMT");

// China have DST in 1989, so it is UTC+9 instead of UTC+8
ZonedDateTime t8964china = ZonedDateTime.of(1989,6,4,0,0,0,0,chinaZone);
System.out.println("t8964china = "+t8964china.toString());
// t8964china = 1989-06-04T00:00+09:00[Asia/Shanghai]

// Convert to GMT
ZonedDateTime t8964gmt   = t8964china.withZoneSameInstant(gmtZone);
System.out.println("t8964gmt   = "+t8964gmt.toString());
// t8964gmt   = 1989-06-03T15:00Z[GMT]

// China is UTC+8 in winter
ZonedDateTime t8914china = ZonedDateTime.of(1989,1,4,0,0,0,0,chinaZone);
System.out.println("t8914china = "+t8914china.toString());
// t8914china = 1989-01-04T00:00+08:00[Asia/Shanghai]

// China do not have DST after 1991, it is UTC+8
ZonedDateTime t1864china = ZonedDateTime.of(2018,6,4,0,0,0,0,chinaZone);
System.out.println("t1864china = "+t1864china.toString());
// t1864china = 2018-06-04T00:00+08:00[Asia/Shanghai]

// Number of hour between 8964 and 8914, odd number
long diff = t8914china.until(t8964china,ChronoUnit.HOURS);
System.out.println("t8964china - t1864china = "+diff+" hours");
// t8964china - t1864china = 3623 hours

// Compare between gmt and china time, same result
diff = t8914china.until(t8964gmt,ChronoUnit.HOURS);
System.out.println("t8964gmt   - t1864china = "+diff+" hours");
// t8964gmt   - t1864china = 3623 hours
```
