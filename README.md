# PGRF1.2024.c04

Projekt pro cvičení UT 15:45 - 17:20.

## Úloha 1: Rasterizace úsečky, tečkované čáry a n-úhelníku

Vytvořte program pro kreslení úsečky zadané dvěma libovolnými koncovými body ```[x1,y1]``` a ```[x2,y2]```.
- Koncové body zadávejte *interaktivně* pomocí tzv. pružné čáry.
- ***Stisknutím tlačítka myši*** zadáte první vrchol, při tažení myší se bude vykreslovat aktuální úsečka společně s již vykreslenou scénou a při uvolnění tlačítka se potvrdí koncový vrchol. 

K implementaci použijte rozhraní a třídy definované na cvičeních. Třídy případně upravte nebo doplňte o potřebné metody.
Návrhy třídy Point, Line a LineRasterizer naleznete v modulu task1 (viz Oliva -> Obsah -> Ukázky a návody).

Vytvořte třídu **FilledLineRasterizer** dědící z abstraktní třídy LineRasterizer a **správně implementující libovolný algoritmus** pro rasterizaci úsečky.

- ***Do komentáře zapište***, o jaký algoritmus se jedná, jeho výhody a nevýhody, případně jiná jeho specifika.

Prozkoumejte třídy Canvas, CanvasKey, CanvasPaint, …, CanvasRasterBufferedImage (v BB ukázky a návody), řešící hlavní aplikační třídu a UI. Soustřeďte se na interface Raster a třídu RasterBufferedImage a použijte ji pro vaše řešení.
Třídy zakomponujte do aplikace, která bude interaktivně zadávat body tvořící vrcholy n-úhelníku. Využijte ukázku CanvasRasterBufferedImage.

- Zkušenější studenti mohou použít aplikační logiku z modulu task2, primárně určeného pro druhou úlohu. Tyto ukázky aplikačního řešení nejsou dogma, můžete je modifikovat nebo navrhnout vlastní. Snažte se o rozdělení aplikace na smysluplné třídy a zachovejte koncept rozhraní a tříd Raster, Point, Line a LineRasterizer.

Vytvořte si vhodnou třídu **Polygon** pro ukládání vrcholů. Je vhodnější ukládat vrcholy ne hrany z důvodu zajištění uzavřenosti útvaru, viz druhá úloha.
- Vrcholy zadávejte interaktivně: stisknutím tlačítka myši vytvořte nový bod spojený s dvěma již vytvořenými vrcholy, např. s prvním a posledním.
- Tažením kreslete pružnou čáru k oběma vrcholům a uvolněním tlačítka přidejte bod do seznamu vrcholů n-úhelníku.

Přidejte řežim (po stisku klávesy ```Shift```) pro kreslení vodorovných, svislých a úhlopříčných úseček. První bod je zadán stiskem, druhý určen na základě polohy při tažení myši, tak aby se vybral nejbližší koncový bod z možné vodorovné, svislé nebo úhlopříčné úsečky.

Implementujte algoritmus vykreslující **tučnou (silnou) úsečku**. Zakomponujte jej do aplikace, například při editaci se bude vykreslovat tenká čára a překreslení celého útvaru bude tučnou čarou.

Implementujte funkci na klávesu ```C``` pro mazání plátna a všech datových struktur.


### Bonusy
#### Bonus 1
Upravte program tak, aby bylo možné souřadnice jednotlivých vrcholů editovat myší. Například při stisku pravého tlačítka myši naleznete nejbližší vrchol a tažením mu nastavíte novou souřadnici. Podobně i přidávání nových vrcholů bude nový vrchol umístěn do nejbližší hrany.

#### Bonus 2
U tučné úsečky modifikujte algoritmus tak, aby čára byla stejně siná, bez ohledu na strmost čáry. Zaoblete konce tučné čáry.

#### Bonus 3
Vytvořte si GITový repozitář a pravidelně commitujte postup.

### Hodnocení

Při hodnocení je kladen důraz na **funkčnost** programu pro libovolně zadané koncové body, na **přesnost** vykreslení a na kvalitu návrhu a čitelnost kódu. Kód vhodně rozdělte do rozhraní a tříd. **Kód očistěte** od ladicích či pokusných nefunkčních částí.

### Termín odevzdání
Do neděle **20.10.2024, 23:59**.
Odevzdávejte prostřednictvím BB (Olivy), před odevzdáním si znovu přečtěte pravidla odevzdávání semestrálních projektů a průběžných úloh (Viz níže).
Odevzdání úlohy si prosím nenechávejte na poslední chvíli!

### Pravidla odevzdání úloh

Projekty odevzdávejte prostřednictvím Blackboard (BB - Oliva):

    Kurz PGRF1 -> Úkoly -> Úloha 1 (klikněte na název úkolu)

- Veškeré soubory související s prací odevzdávejte zabalené v archivu ve formátu **zip** (případně rar, 7z, …).
  - Soubor pojmenujte ve tvaru **cviceni_prijmeni_jmeno.zip** bez ***diakritiky*** (např. c03_novak_vaclav.zip případně kf_novak_vaclav.zip).

  - Pokud nevíte číslo cvičení, které navštěvujete, podívejte se do [rozvrhu](https://fim.uhk.cz/rozvrhy/ttable.asp?identifier=KIKM%2FPGRF1&weeks=1-13&idtype=name&objectclass=module&periods=2-17&width=100).

Soubor bude obsahovat adresářovou strukturu. Příklad:

```
c0X_novak_vaclav.zip
│   
└───c0X_novak_vaclav
    │   README.md
    │
    └── src
    │   └─   ZDROJOVÉ
    │   └─    SOUBORY   
    │   └─   PROGRAMU
    │
    └── doc
        └─   PozadavkyPGRF1_Task1_2024.docx
```

- Jeden hlavní adresář pojmenovaný stejně jako soubor archivu (např. c03_novak_vaclav případně kf_novak_vaclav)

- Podadresář src obsahující zdrojové soubory *.java umístěné do adresářů podle definovaných package (struktura projektu v prostředí např. IntelliJ, Eclipse a pod.)

- Případně další podadresáře s přiloženými soubory např. doc, res, ... 
- Pokud se vám podaří vygenerovat spustitelnou formu aplikace (jar, případně exe) umístěte ji do hlavního adresáře.

  - V žádném případě **nepřibalujte** adresář **bin** se soubory _*.class_.

  - Pokud aplikace vyžaduje další binární soubory (obrázky, data) ponechejte je tak, aby byly ve stejné relativní cestě vůči hlavnímu adresáři, jako je to ve vašem projektu. Tzn. ponechejte celou adresářovou strukturu projektu bez adresáře bin.

Před odevzdáním si vyzkoušejte rozbalení archivu a funkčnost odevzdávaného projektu.

- V případě složitějšího členění zdrojových kódů musí být z názvu jasné, která třída je aplikační.

- **Zdrojový kód** bude _logicky členěn, formátován_ a _strukturován_ a bude v rozumné míře _komentován_ s vhodně volenými názvy tříd, proměnných, metod atd. Je velmi doporučeno _použít funkci přeformátování_ zdrojového souboru.

- Případné komentáře či poznámky k implementaci uložte do textových souborů v hlavním adresáři (notes.txt, todo.txt, readme.md)

- Pokud je součástí zadání **vyplnění auto-evaluační tabulky** (tabulka požadavků), vyplňte splnění požadované funkcionality společně se způsobem ovládání a případně doplňte vámi doplněné funkce nad rámec zadání. Tabulku nahrajte do hlavního adresáře a zabalte do výsledného archivu.

- Na soubory umístěné mimo adresář (např. **c03_novak_vaclav**) **NEBUDE** brán zřetel.

- **Nepište poznámky** do formuláře pro odevzdání úloh v BB.
