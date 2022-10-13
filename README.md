# GridItemDecoration

[![](https://jitpack.io/v/elapsefeather/GridItemDecoration.svg)](https://jitpack.io/#elapsefeather/GridItemDecoration)

## sample

| 樣式範例                       | 
| -------------                 |
| DecorationStyle.VERTICAL      | 
| <img src="https://github.com/elapsefeather/GridItemDecoration/blob/925babd1501a3593b28831d9074728dc11fb81c1/screenshots/532.jpg" height="500"> & <img src="https://github.com/elapsefeather/GridItemDecoration/blob/3270cd0bfc779de158068dcc2bdcd9e4bbfee31a/screenshots/552.jpg" height="500">|
| DecorationStyle.HORIZONTAL   | 
| <img src="https://github.com/elapsefeather/GridItemDecoration/blob/925babd1501a3593b28831d9074728dc11fb81c1/screenshots/533.jpg" height="500"> & <img src="https://github.com/elapsefeather/GridItemDecoration/blob/3270cd0bfc779de158068dcc2bdcd9e4bbfee31a/screenshots/551.jpg" height="500">|
| DecorationStyle.ROUNDALL     |
| <img src="https://github.com/elapsefeather/GridItemDecoration/blob/925babd1501a3593b28831d9074728dc11fb81c1/screenshots/530.jpg" height="500"> & <img src="https://github.com/elapsefeather/GridItemDecoration/blob/3270cd0bfc779de158068dcc2bdcd9e4bbfee31a/screenshots/553.jpg" height="500">|
| DecorationStyle.INSIDEALL    |
| <img src="https://github.com/elapsefeather/GridItemDecoration/blob/925babd1501a3593b28831d9074728dc11fb81c1/screenshots/531.jpg" height="500"> & <img src="https://github.com/elapsefeather/GridItemDecoration/blob/3270cd0bfc779de158068dcc2bdcd9e4bbfee31a/screenshots/554.jpg" height="500">|
| setDividerColor(Color)       |
| <img src="https://github.com/elapsefeather/GridItemDecoration/blob/925babd1501a3593b28831d9074728dc11fb81c1/screenshots/529.jpg" height="500">|
| Use LinearLayoutManager       |
|<img src="https://github.com/elapsefeather/GridItemDecoration/blob/fa47564add19010b584d00d99bde95f3e83849ea/screenshots/555.jpg" height="500"> & <img src="https://github.com/elapsefeather/GridItemDecoration/blob/fa47564add19010b584d00d99bde95f3e83849ea/screenshots/556.jpg" height="500"> & <img src="https://github.com/elapsefeather/GridItemDecoration/blob/fa47564add19010b584d00d99bde95f3e83849ea/screenshots/557.jpg" height="500"> & <img src="https://github.com/elapsefeather/GridItemDecoration/blob/fa47564add19010b584d00d99bde95f3e83849ea/screenshots/558.jpg" height="500">|
## Setup

The easiest way to add the **GridItemDecoration** library to your project is by adding it as a
dependency to your build.gradle

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
     implementation 'com.github.elapsefeather:GridItemDecoration:1.1.1'
}
```

## Usage

```
        GridItemDecoration itemDecoration = new GridItemDecoration(GridItemDecoration.DecorationStyle.INSIDEALL);
        itemDecoration.setDividerColor(getColor(R.color.black));
        RecyclerView.addItemDecoration(itemDecoration);
```

or

```
        RecyclerView.addItemDecoration(new GridItemDecoration(GridItemDecoration.DecorationStyle.ROUNDALL, getColor(R.color.black)));
```

or

```
        RecyclerView.addItemDecoration(new GridItemDecoration.Builder()
                .orientation(GridItemDecoration.DecorationStyle.ROUNDALL)
                .color(getColor(R.color.black))
                .size(5)
                .build());
```

## Version History

> ### v1.1.0 (2022/10/13)
> #### Bug Fixes:
> - 修正 LinearLayoutManager 使用上的繪製錯誤
> - 修正 GridLayoutManager 使用上數量不足的繪製錯誤

> ### v1.1.0 (2022/10/12)
> #### Framework Enhancements:
> - 取消使用 drawable divider
> - 可設置 divider 寬度
> - 增加 Builder 創建方式
> - public interface DecorationStyle
>
> #### Bug Fixes:
> - 修正項目不足仍會繪製分隔線問題

> ### v1.0.0(2022/10/01)
> - new project.


