// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 161 -> sentence 161
 * PRIMARY LINKS: declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 161 -> sentence 161
 *                inheritance, inheriting -> paragraph 161 -> sentence 161
 * NUMBER: 1
 * DESCRIPTION: subclass primary constructor parameters may be forwarded into superclass primary constructor arguments while the subclass may also declare its own primary-constructor properties in class declaration
 */

// TESTCASE NUMBER: 1
open class Base(val name: String)

class Child(name: String, val age: Int) : Base(name)

// TESTCASE NUMBER: 2
open class Point(val x: Int, val y: Int)

class Shifted(dx: Int, dy: Int, val label: String) : Point(dx + 1, dy + 2)

// TESTCASE NUMBER: 3
open class Holder(val id: Int, val tag: String)

class Renamed(code: Int, mark: String, val extra: Boolean) : Holder(code, mark)

fun viaChild(): Pair<String, Int> {
    val c = Child("Ann", 20)
    return c.name to c.age
}

fun viaShifted(): Triple<Int, Int, String> {
    val s = Shifted(3, 4, "p")
    return Triple(s.x, s.y, s.label)
}

fun viaRenamed(): Triple<Int, String, Boolean> {
    val r = Renamed(7, "ok", true)
    return Triple(r.id, r.tag, r.extra)
}

fun box(): String {
    if (viaChild() != ("Ann" to 20)) return "NOK: child"
    if (Child("Bo", 0).name != "Bo" || Child("Bo", 0).age != 0) return "NOK: child-bo"

    if (viaShifted() != Triple(4, 6, "p")) return "NOK: shifted"
    if (Shifted(0, 0, "z").x != 1 || Shifted(0, 0, "z").y != 2) return "NOK: shifted-zero"

    if (viaRenamed() != Triple(7, "ok", true)) return "NOK: renamed"
    if (Renamed(1, "a", false).extra) return "NOK: renamed-extra"
    return "OK"
}
