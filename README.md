# MultSwitch
[![](https://jitpack.io/v/elapsefeather/GridItemDecoration.svg)](https://jitpack.io/#elapsefeather/GridItemDecoration)

## sample

| 樣式範例            | 樣式範例     | 
| -------------         |-------------      |
| GridItemDecoration.VERTICAL             | GridItemDecoration.HORIZONTAL    | 
| <img src="https://github.com/elapsefeather/GridItemDecoration/blob/925babd1501a3593b28831d9074728dc11fb81c1/screenshots/532.jpg" height="500">| <img src="https://github.com/elapsefeather/GridItemDecoration/blob/925babd1501a3593b28831d9074728dc11fb81c1/screenshots/533.jpg" height="500">        |
| GridItemDecoration.ROUNDALL             | GridItemDecoration.INSIDEALL    |
| <img src="https://github.com/elapsefeather/GridItemDecoration/blob/925babd1501a3593b28831d9074728dc11fb81c1/screenshots/530.jpg" height="500">| <img src="https://github.com/elapsefeather/GridItemDecoration/blob/925babd1501a3593b28831d9074728dc11fb81c1/screenshots/531.jpg" height="500">        |
| setDividerColor(Color)             |     |
| <img src="https://github.com/elapsefeather/GridItemDecoration/blob/925babd1501a3593b28831d9074728dc11fb81c1/screenshots/529.jpg" height="500">|         |

## Setup
The easiest way to add the **GridItemDecoration** library to your project is by adding it as a dependency to your build.gradle

**Step 1.** Add the JitPack repository to your build file
```gradle
allprojects {
    repositories {
        maven { url 'https://jitpack.io' }
    }
}
```

**Step 2.** Add the dependency
```gradle
dependencies {
     implementation 'com.github.elapsefeather:GridItemDecoration:1.0.0'
}
```

## Usage
```
        GridItemDecoration itemDecoration = new GridItemDecoration(this, GridItemDecoration.INSIDEALL);
        itemDecoration.setDividerColor(getColor(R.color.black));
        RecyclerView.addItemDecoration(itemDecoration);
```


