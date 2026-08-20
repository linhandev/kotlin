// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 222 -> sentence 222
 * PRIMARY LINKS: inheritance, inheriting -> paragraph 222 -> sentence 222
 *                inheritance, overriding -> paragraph 222 -> sentence 222
 *                declarations, classifier-declaration, class-declaration, inheritance-delegation -> paragraph 222 -> sentence 222
 * NUMBER: 1
 * DESCRIPTION: class delegation via by cannot auto-resolve same-named dual interface defaults (MANY_INTERFACES on conflicting impl type; 2x DELEGATED_MEMBER_HIDES_SUPERTYPE_OVERRIDE + 2x MANY_IMPL on delegated class); covers fun/String/val and separate per-interface delegates; contrasts with p-221 distinct-member dual by and with p-206 non-delegated dual defaults
 */

// TESTCASE NUMBER: 1
interface LeftFun {
    fun f(): Int = 1
}

interface RightFun {
    fun f(): Int = 2
}

<!MANY_INTERFACES_MEMBER_NOT_IMPLEMENTED!>class ConflictFunImpl<!> : LeftFun, RightFun

<!DELEGATED_MEMBER_HIDES_SUPERTYPE_OVERRIDE, DELEGATED_MEMBER_HIDES_SUPERTYPE_OVERRIDE, MANY_IMPL_MEMBER_NOT_IMPLEMENTED, MANY_IMPL_MEMBER_NOT_IMPLEMENTED!>class DualByConflictFun<!>(x: ConflictFunImpl) : LeftFun by x, RightFun by x

class LeftOnlyFun : LeftFun
class RightOnlyFun : RightFun

<!DELEGATED_MEMBER_HIDES_SUPERTYPE_OVERRIDE, DELEGATED_MEMBER_HIDES_SUPERTYPE_OVERRIDE, MANY_IMPL_MEMBER_NOT_IMPLEMENTED, MANY_IMPL_MEMBER_NOT_IMPLEMENTED!>class DualBySeparateFun<!>(a: LeftFun, b: RightFun) : LeftFun by a, RightFun by b

// TESTCASE NUMBER: 2
interface LeftTag {
    fun tag(): String = "L"
}

interface RightTag {
    fun tag(): String = "R"
}

<!MANY_INTERFACES_MEMBER_NOT_IMPLEMENTED!>class ConflictTagImpl<!> : LeftTag, RightTag

<!DELEGATED_MEMBER_HIDES_SUPERTYPE_OVERRIDE, DELEGATED_MEMBER_HIDES_SUPERTYPE_OVERRIDE, MANY_IMPL_MEMBER_NOT_IMPLEMENTED, MANY_IMPL_MEMBER_NOT_IMPLEMENTED!>class DualByConflictTag<!>(x: ConflictTagImpl) : LeftTag by x, RightTag by x

// TESTCASE NUMBER: 3
interface LeftVal {
    val n: Int get() = 1
}

interface RightVal {
    val n: Int get() = 2
}

<!MANY_INTERFACES_MEMBER_NOT_IMPLEMENTED!>class ConflictValImpl<!> : LeftVal, RightVal

<!DELEGATED_MEMBER_HIDES_SUPERTYPE_OVERRIDE, DELEGATED_MEMBER_HIDES_SUPERTYPE_OVERRIDE, MANY_IMPL_MEMBER_NOT_IMPLEMENTED, MANY_IMPL_MEMBER_NOT_IMPLEMENTED!>class DualByConflictVal<!>(x: ConflictValImpl) : LeftVal by x, RightVal by x

class LeftOnlyVal : LeftVal
class RightOnlyVal : RightVal

<!DELEGATED_MEMBER_HIDES_SUPERTYPE_OVERRIDE, DELEGATED_MEMBER_HIDES_SUPERTYPE_OVERRIDE, MANY_IMPL_MEMBER_NOT_IMPLEMENTED, MANY_IMPL_MEMBER_NOT_IMPLEMENTED!>class DualBySeparateVal<!>(a: LeftVal, b: RightVal) : LeftVal by a, RightVal by b
