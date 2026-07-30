// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 263 -> sentence 263
 * PRIMARY LINKS: declarations, declaration-visibility -> paragraph 263 -> sentence 263
 *                inheritance, inheriting -> paragraph 263 -> sentence 263
 * NUMBER: 1
 * DESCRIPTION: precise types when a top-level private class is used within the same file without exposing it from public API signatures
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
private class Hidden(val id: Int = 1)

private class Client {
    fun make(): Hidden = Hidden(1)
}

fun hiddenId(): Int = Hidden(1).id

fun case1() {
    val h = Client().make()
    h checkType { check<Hidden>() }
    checkSubtype<Hidden>(h)
    h.id checkType { check<Int>() }
    hiddenId() checkType { check<Int>() }
}

// TESTCASE NUMBER: 2
private open class HiddenBase(val label: String)

private class HiddenChild(label: String, val extra: Int) : HiddenBase(label)

private fun makeChild(): HiddenChild = HiddenChild("h", 2)

fun case2() {
    val c = makeChild()
    c checkType { check<HiddenChild>() }
    checkSubtype<HiddenBase>(c)
    c.label checkType { check<String>() }
    c.extra checkType { check<Int>() }
}

// TESTCASE NUMBER: 3
private class HiddenBox<T>(val value: T)

private fun makeBox(): HiddenBox<String> = HiddenBox("ok")

fun case3() {
    val b = makeBox()
    b checkType { check<HiddenBox<String>>() }
    b.value checkType { check<String>() }
}
