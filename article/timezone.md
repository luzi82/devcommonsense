# 時區

* 在互聯網時代，產品使用者可能會來自世界各地，他們身處不同時區。
* 即使你的目標客戶只屬於一個地區，但這些客戶可能會因為旅行公幹，而走到另一個時區。
* 個別地區可能會因為政治等因素，而改變時區。

## 實例

### Aniplex《きららファンタジア》

Aniplex 在 2017 年 12 月 11 日，於 iOS 及 Android 平台推出手機遊戲軟件《[きららファンタジア](https://kirarafantasia.com/)》。發行地區只限日本，所以只有日本玩家可以從日本的 App Store / Google Play。至於日本以外的玩家，iOS 的話可以用假日本賬號下載，而 Android 則要用其他不正常渠通下載。

![きららファンタジア](../image/kirarafantasia_cover.jpg)

玩家只要每日打開遊戲，就會獲贈一些每日登入獎勵。

![きららファンタジア login bonus](../image/kirarafantasia_loginbonus.jpg)

然而，在日本 UTC+9 以外的玩家，例如香港和中國 UTC+8 的玩家，卻可以在一天得到登入禮物兩次。經玩家測試後，懷疑是時區不同導致遊戲出錯。（另見 [不要相信用戶端](dont_trust_client.md) ）

雖然這個問題是日本時區以外才發生。但由於設定時區成本很低，所以這個漏洞也被日本玩家利用。

### 任天堂《Splatoon 2》

任天堂在 2017 年 7 月 21 日，於旗下的 Switch 主機推出遊戲軟件《[Splatoon 2](https://splatoon.nintendo.com/)》。發行地區包括美國，歐洲，日本和台灣。

![Splatoon 2](../image/splatoon_cover.jpg)

遊戲中定期會舉辦一些活動，亦有活動時間表。由於遊戲的對象是全世界的玩家，所以時間表的顯示也做過時區處理。

以下是系統設定為 UTC+8 時顯示的時間表。

![Splatoon 2 time table tz8](../image/splatoon_tz8.jpg)

而當系統設定為 UTC+5 時，顯示的日期時間亦會隨之改變。

![Splatoon 2 time table tz5](../image/splatoon_tz5.jpg)

玩家無論身處甚麼時區，都可以看到根據自己時區顯示的時間表，亦可以正常參與活動，不會遇到任何問題。

## 注意及建議

* 時區差別未必是以小時計。尼泊爾的時區是 UTC+9:45。
* 某些地區有[日光節約時間](https://zh.wikipedia.org/wiki/%E5%A4%8F%E6%97%B6%E5%88%B6) (Daylight saving time) ，在夏季時間會調快一小時。
* 如果服務對象不是單一時區。營運公告中有提及到時間的地方，應該要註明時區。
* 如果有些資料與時間掛勾，例如課金遊戲的體力恢復，或者登入獎勵，就應該以 64 bit 的 Epoch time 做時間判定。（另見 [與時間掛勾的資料](time_related_data.md) ）
