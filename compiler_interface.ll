; ModuleID = './kotlin-native/dist/konan/targets/macos_arm64/native/compiler_interface.bc'
source_filename = "llvm-link"
target datalayout = "e-m:o-i64:64-i128:128-n32:64-S128"
target triple = "arm64-apple-macosx11.0.0"

%0 = type opaque
%struct.WritableTypeInfo = type { %struct.TypeInfoObjCExportAddition }
%struct.TypeInfoObjCExportAddition = type { i8*, i8*, %struct.ObjCTypeAdapter* }
%struct.ObjCTypeAdapter = type { %struct.TypeInfo*, i8**, i32, %struct.InterfaceTableRecord*, i32, i8*, %struct.ObjCToKotlinMethodAdapter*, i32, %struct.ObjCToKotlinMethodAdapter*, i32, %struct.ObjCToKotlinMethodAdapter*, i32, %struct.KotlinToObjCMethodAdapter*, i32 }
%struct.TypeInfo = type { %struct.TypeInfo*, %struct.ExtendedTypeInfo*, i32, i32, %struct.TypeInfo*, i32*, i32, %struct.TypeInfo**, i32, i32, %struct.InterfaceTableRecord*, %struct.ObjHeader*, %struct.ObjHeader*, i32, i32, %struct.WritableTypeInfo*, %struct.AssociatedObjectTableRecord*, void (i8*, %struct.ObjHeader*)*, i32 }
%struct.ExtendedTypeInfo = type { i32, i32*, i8*, i8**, i32, i8** }
%struct.ObjHeader = type { %struct.TypeInfo addrspace(1)* }
%struct.AssociatedObjectTableRecord = type { %struct.TypeInfo*, %struct.ObjHeader* (%struct.ObjHeader**)* }
%struct.InterfaceTableRecord = type { i32, i32, i8** }
%struct.ObjCToKotlinMethodAdapter = type { i8*, i8*, void ()* }
%struct.KotlinToObjCMethodAdapter = type { i8*, i32, i32, i32, i32, i8* }
%struct.KotlinObjCClassData = type { %struct.TypeInfo*, i8*, i32 }
%struct.KotlinObjCClassInfo = type { i8*, i32, i8*, i8**, %struct.ObjCMethodDescription*, i32, %struct.ObjCMethodDescription*, i32, i32*, %struct.TypeInfo*, %struct.TypeInfo*, i8**, %struct.KotlinObjCClassData* (i8*, i8*)* }
%struct.ObjCMethodDescription = type { i8* (i8*, i8*, ...)*, i8*, i8* }
%struct.Block_literal_1 = type { i8*, i32, i32, void (i8*, ...)*, %struct.Block_descriptor_1* }
%struct.Block_descriptor_1 = type { i64, i64, void (i8*, i8*)*, void (i8*)*, i8*, i8* }
%struct._class_t = type { %struct._class_t*, %struct._class_t*, %struct._objc_cache*, i8* (i8*, i8*)**, %struct._class_ro_t* }
%struct._objc_cache = type opaque
%struct._class_ro_t = type { i32, i32, i32, i8*, i8*, %struct.__method_list_t*, %struct._objc_protocol_list*, %struct._ivar_list_t*, i8*, %struct._prop_list_t* }
%struct.__method_list_t = type { i32, i32, [0 x %struct._objc_method] }
%struct._objc_method = type { i8*, i8*, i8* }
%struct._objc_protocol_list = type { i64, [0 x %struct._protocol_t*] }
%struct._protocol_t = type { i8*, i8*, %struct._objc_protocol_list*, %struct.__method_list_t*, %struct.__method_list_t*, %struct.__method_list_t*, %struct.__method_list_t*, %struct._prop_list_t*, i32, i32, i8**, i8*, %struct._prop_list_t* }
%struct._ivar_list_t = type { i32, i32, [0 x %struct._ivar_t] }
%struct._ivar_t = type { i32*, i8*, i8*, i32, i32 }
%struct._prop_list_t = type { i32, i32, [0 x %struct._prop_t] }
%struct._prop_t = type { i8*, i8* }
%struct.InitNode = type { void (i32, %struct.MemoryState*)*, %struct.InitNode* }
%struct.MemoryState = type opaque
%struct.ArrayHeader = type { %struct.TypeInfo addrspace(1)*, i32 }
%struct.FrameOverlay = type { %struct.FrameOverlay*, i32, i32 }
%class.KRefSharedHolder = type { %struct.ObjHeader*, %union.anon }
%union.anon = type { %class.ForeignRefManager* }
%class.ForeignRefManager = type opaque
%"class.kotlin::RWSpinLock" = type { %"struct.std::__1::atomic" }
%"struct.std::__1::atomic" = type { %"struct.std::__1::__atomic_base" }
%"struct.std::__1::__atomic_base" = type { %"struct.std::__1::__atomic_base.0" }
%"struct.std::__1::__atomic_base.0" = type { %"struct.std::__1::__cxx_atomic_impl" }
%"struct.std::__1::__cxx_atomic_impl" = type { %"struct.std::__1::__cxx_atomic_base_impl" }
%"struct.std::__1::__cxx_atomic_base_impl" = type { i64 }
%"class.kotlin::ManuallyScoped" = type { [8 x i8] }

@touchWritableTypeInfo = local_unnamed_addr global %struct.WritableTypeInfo zeroinitializer, align 8, !dbg !0
@touchKotlinObjCClassData = local_unnamed_addr global %struct.KotlinObjCClassData zeroinitializer, align 8, !dbg !8
@touchKotlinObjCClassInfo = local_unnamed_addr global %struct.KotlinObjCClassInfo zeroinitializer, align 8, !dbg !247
@touchObjCMethodDescription = local_unnamed_addr global %struct.ObjCMethodDescription zeroinitializer, align 8, !dbg !279
@touchObjCTypeAdapter = local_unnamed_addr global %struct.ObjCTypeAdapter zeroinitializer, align 8, !dbg !281
@touchObjCToKotlinMethodAdapter = local_unnamed_addr global %struct.ObjCToKotlinMethodAdapter zeroinitializer, align 8, !dbg !283
@touchKotlinToObjCMethodAdapter = local_unnamed_addr global %struct.KotlinToObjCMethodAdapter zeroinitializer, align 8, !dbg !285
@touchTypeInfoObjCExportAddition = local_unnamed_addr global %struct.TypeInfoObjCExportAddition zeroinitializer, align 8, !dbg !287
@touchBlock_literal_1 = local_unnamed_addr global %struct.Block_literal_1 zeroinitializer, align 8, !dbg !289
@touchBlock_descriptor_1 = local_unnamed_addr global %struct.Block_descriptor_1 zeroinitializer, align 8, !dbg !317
@llvm.compiler.used = appending global [1 x i8*] [i8* bitcast (%struct._class_t** @"OBJC_CLASSLIST_REFERENCES_$_" to i8*)], section "llvm.metadata"
@"OBJC_CLASSLIST_REFERENCES_$_" = internal global %struct._class_t* @"OBJC_CLASS_$_NSString", section "__DATA,__objc_classrefs,regular,no_dead_strip", align 8
@"OBJC_CLASS_$_NSString" = external global %struct._class_t
@touchInitNode = local_unnamed_addr global %struct.InitNode zeroinitializer, align 8, !dbg !937
@touchTypeInfo = local_unnamed_addr global %struct.TypeInfo zeroinitializer, align 8, !dbg !1202
@touchExtendedTypeInfo = local_unnamed_addr global %struct.ExtendedTypeInfo zeroinitializer, align 8, !dbg !1205
@touchInterfaceTableRecord = local_unnamed_addr global %struct.InterfaceTableRecord zeroinitializer, align 8, !dbg !1207
@touchAssociatedObjectTableRecord = local_unnamed_addr global %struct.AssociatedObjectTableRecord zeroinitializer, align 8, !dbg !1209
@touchObjHeader = local_unnamed_addr global %struct.ObjHeader zeroinitializer, align 8, !dbg !1211
@touchArrayHeader = local_unnamed_addr global %struct.ArrayHeader zeroinitializer, align 8, !dbg !1213
@touchFrameOverlay = local_unnamed_addr global %struct.FrameOverlay zeroinitializer, align 8, !dbg !1215
@touchKRefSharedHolder = local_unnamed_addr global %class.KRefSharedHolder zeroinitializer, align 8, !dbg !1223
@llvm.used = appending global [1 x i8*] [i8* bitcast (%"class.kotlin::RWSpinLock"* (%"class.kotlin::ManuallyScoped"*)* @_ZN6kotlin14ManuallyScopedINS_10RWSpinLockILNS_24MutexThreadStateHandlingE0EEELb0EE4implEv to i8*)], section "llvm.metadata"

; Function Attrs: uwtable
define %0* @touchNSString() local_unnamed_addr #0 !dbg !1760 {
  %1 = load i8*, i8** bitcast (%struct._class_t** @"OBJC_CLASSLIST_REFERENCES_$_" to i8**), align 8, !dbg !1775
  %2 = tail call i8* @objc_alloc_init(i8* %1), !dbg !1775
  %3 = bitcast i8* %2 to %0*, !dbg !1775
  ret %0* %3, !dbg !1776
}

declare i8* @objc_alloc_init(i8*) local_unnamed_addr

; Function Attrs: norecurse nounwind readnone uwtable
define i8* @touchCreateKotlinObjCClass() local_unnamed_addr #1 !dbg !1777 {
  ret i8* bitcast (i8* (%struct.KotlinObjCClassInfo*)* @CreateKotlinObjCClass to i8*), !dbg !1779
}

declare i8* @CreateKotlinObjCClass(%struct.KotlinObjCClassInfo*) #2

; Function Attrs: norecurse nounwind readnone uwtable
define i8* @touchGetObjCKotlinTypeInfo() local_unnamed_addr #1 !dbg !1780 {
  ret i8* bitcast (%struct.TypeInfo* (%struct.ObjHeader*)* @GetObjCKotlinTypeInfo to i8*), !dbg !1781
}

; Function Attrs: nounwind
declare %struct.TypeInfo* @GetObjCKotlinTypeInfo(%struct.ObjHeader*) #3

; Function Attrs: norecurse nounwind readnone uwtable
define i8* @touchMissingInitImp() local_unnamed_addr #1 !dbg !1782 {
  ret i8* bitcast (i8* (i8*, i8*)* @MissingInitImp to i8*), !dbg !1783
}

declare i8* @MissingInitImp(i8*, i8*) #2

; Function Attrs: norecurse nounwind readnone uwtable
define i8* @touchKotlin_Interop_DoesObjectConformToProtocol() local_unnamed_addr #1 !dbg !1784 {
  ret i8* bitcast (i1 (i8*, i8*, i1)* @Kotlin_Interop_DoesObjectConformToProtocol to i8*), !dbg !1785
}

declare zeroext i1 @Kotlin_Interop_DoesObjectConformToProtocol(i8*, i8*, i1 zeroext) #2

; Function Attrs: norecurse nounwind readnone uwtable
define i8* @touchKotlin_Interop_IsObjectKindOfClass() local_unnamed_addr #1 !dbg !1786 {
  ret i8* bitcast (i1 (i8*, i8*)* @Kotlin_Interop_IsObjectKindOfClass to i8*), !dbg !1787
}

declare zeroext i1 @Kotlin_Interop_IsObjectKindOfClass(i8*, i8*) #2

; Function Attrs: norecurse nounwind readnone uwtable
define i8* @touchKotlin_ObjCExport_refToLocalObjC() local_unnamed_addr #1 !dbg !1788 {
  ret i8* bitcast (i8* (%struct.ObjHeader addrspace(1)*)* @Kotlin_ObjCExport_refToLocalObjC to i8*), !dbg !1789
}

declare i8* @Kotlin_ObjCExport_refToLocalObjC(%struct.ObjHeader addrspace(1)*) #2

; Function Attrs: norecurse nounwind readnone uwtable
define i8* @touchKotlin_ObjCExport_refToRetainedObjC() local_unnamed_addr #1 !dbg !1790 {
  ret i8* bitcast (i8* (%struct.ObjHeader addrspace(1)*)* @Kotlin_ObjCExport_refToRetainedObjC to i8*), !dbg !1791
}

declare i8* @Kotlin_ObjCExport_refToRetainedObjC(%struct.ObjHeader addrspace(1)*) #2

; Function Attrs: norecurse nounwind readnone uwtable
define i8* @touchKotlin_ObjCExport_refFromObjC() local_unnamed_addr #1 !dbg !1792 {
  ret i8* bitcast (%struct.ObjHeader* (i8*, %struct.ObjHeader**)* @Kotlin_ObjCExport_refFromObjC to i8*), !dbg !1793
}

declare %struct.ObjHeader* @Kotlin_ObjCExport_refFromObjC(i8*, %struct.ObjHeader**) #2

; Function Attrs: norecurse nounwind readnone uwtable
define i8* @touchKotlin_ObjCExport_CreateRetainedNSStringFromKString() local_unnamed_addr #1 !dbg !1794 {
  ret i8* bitcast (i8* (%struct.ObjHeader addrspace(1)*)* @Kotlin_ObjCExport_CreateRetainedNSStringFromKString to i8*), !dbg !1795
}

declare i8* @Kotlin_ObjCExport_CreateRetainedNSStringFromKString(%struct.ObjHeader addrspace(1)*) #2

; Function Attrs: norecurse nounwind readnone uwtable
define i8* @touchKotlin_ObjCExport_convertUnitToRetained() local_unnamed_addr #1 !dbg !1796 {
  ret i8* bitcast (i8* (%struct.ObjHeader addrspace(1)*)* @Kotlin_ObjCExport_convertUnitToRetained to i8*), !dbg !1797
}

declare i8* @Kotlin_ObjCExport_convertUnitToRetained(%struct.ObjHeader addrspace(1)*) #2

; Function Attrs: norecurse nounwind readnone uwtable
define i8* @touchKotlin_ObjCExport_GetAssociatedObject() local_unnamed_addr #1 !dbg !1798 {
  ret i8* bitcast (i8* (%struct.ObjHeader addrspace(1)*)* @Kotlin_ObjCExport_GetAssociatedObject to i8*), !dbg !1799
}

declare i8* @Kotlin_ObjCExport_GetAssociatedObject(%struct.ObjHeader addrspace(1)*) #2

; Function Attrs: norecurse nounwind readnone uwtable
define i8* @touchKotlin_ObjCExport_AbstractMethodCalled() local_unnamed_addr #1 !dbg !1800 {
  ret i8* bitcast (void (i8*, i8*)* @Kotlin_ObjCExport_AbstractMethodCalled to i8*), !dbg !1801
}

declare void @Kotlin_ObjCExport_AbstractMethodCalled(i8*, i8*) #2

; Function Attrs: norecurse nounwind readnone uwtable
define i8* @touchKotlin_ObjCExport_AbstractClassConstructorCalled() local_unnamed_addr #1 !dbg !1802 {
  ret i8* bitcast (void (i8*, %struct.TypeInfo*)* @Kotlin_ObjCExport_AbstractClassConstructorCalled to i8*), !dbg !1803
}

declare void @Kotlin_ObjCExport_AbstractClassConstructorCalled(i8*, %struct.TypeInfo*) #2

; Function Attrs: norecurse nounwind readnone uwtable
define i8* @touchKotlin_ObjCExport_RethrowExceptionAsNSError() local_unnamed_addr #1 !dbg !1804 {
  ret i8* bitcast (void (%struct.ObjHeader*, i8**, %struct.TypeInfo**)* @Kotlin_ObjCExport_RethrowExceptionAsNSError to i8*), !dbg !1805
}

declare void @Kotlin_ObjCExport_RethrowExceptionAsNSError(%struct.ObjHeader*, i8**, %struct.TypeInfo**) #2

; Function Attrs: norecurse nounwind readnone uwtable
define i8* @touchKotlin_ObjCExport_WrapExceptionToNSError() local_unnamed_addr #1 !dbg !1806 {
  ret i8* bitcast (i8* (%struct.ObjHeader*)* @Kotlin_ObjCExport_WrapExceptionToNSError to i8*), !dbg !1807
}

declare i8* @Kotlin_ObjCExport_WrapExceptionToNSError(%struct.ObjHeader*) #2

; Function Attrs: norecurse nounwind readnone uwtable
define i8* @touchKotlin_ObjCExport_NSErrorAsException() local_unnamed_addr #1 !dbg !1808 {
  ret i8* bitcast (%struct.ObjHeader* (i8*, %struct.ObjHeader**)* @Kotlin_ObjCExport_NSErrorAsException to i8*), !dbg !1809
}

declare %struct.ObjHeader* @Kotlin_ObjCExport_NSErrorAsException(i8*, %struct.ObjHeader**) #2

; Function Attrs: norecurse nounwind readnone uwtable
define i8* @touchKotlin_ObjCExport_AllocInstanceWithAssociatedObject() local_unnamed_addr #1 !dbg !1810 {
  ret i8* bitcast (%struct.ObjHeader* (%struct.TypeInfo*, i8*, %struct.ObjHeader**)* @Kotlin_ObjCExport_AllocInstanceWithAssociatedObject to i8*), !dbg !1811
}

; Function Attrs: nounwind
declare %struct.ObjHeader* @Kotlin_ObjCExport_AllocInstanceWithAssociatedObject(%struct.TypeInfo*, i8*, %struct.ObjHeader**) #3

; Function Attrs: norecurse nounwind readnone uwtable
define i8* @touchKotlin_ObjCExport_createContinuationArgument() local_unnamed_addr #1 !dbg !1812 {
  ret i8* bitcast (%struct.ObjHeader* (i8*, %struct.TypeInfo**, %struct.ObjHeader**)* @Kotlin_ObjCExport_createContinuationArgument to i8*), !dbg !1813
}

declare %struct.ObjHeader* @Kotlin_ObjCExport_createContinuationArgument(i8*, %struct.TypeInfo**, %struct.ObjHeader**) #2

; Function Attrs: norecurse nounwind readnone uwtable
define i8* @touchKotlin_ObjCExport_createUnitContinuationArgument() local_unnamed_addr #1 !dbg !1814 {
  ret i8* bitcast (%struct.ObjHeader* (i8*, %struct.TypeInfo**, %struct.ObjHeader**)* @Kotlin_ObjCExport_createUnitContinuationArgument to i8*), !dbg !1815
}

declare %struct.ObjHeader* @Kotlin_ObjCExport_createUnitContinuationArgument(i8*, %struct.TypeInfo**, %struct.ObjHeader**) #2

; Function Attrs: norecurse nounwind readnone uwtable
define i8* @touchKotlin_ObjCExport_resumeContinuation() local_unnamed_addr #1 !dbg !1816 {
  ret i8* bitcast (void (%struct.ObjHeader*, %struct.ObjHeader*, i8*)* @Kotlin_ObjCExport_resumeContinuation to i8*), !dbg !1817
}

declare void @Kotlin_ObjCExport_resumeContinuation(%struct.ObjHeader*, %struct.ObjHeader*, i8*) #2

; Function Attrs: norecurse nounwind readnone uwtable
define i8* @touchKotlin_ObjCExport_NSIntegerTypeProvider() local_unnamed_addr #1 !dbg !1818 {
  ret i8* bitcast (i64 ()* @Kotlin_ObjCExport_NSIntegerTypeProvider to i8*), !dbg !1819
}

declare i64 @Kotlin_ObjCExport_NSIntegerTypeProvider() #2

; Function Attrs: norecurse nounwind readnone uwtable
define i8* @touchKotlin_longTypeProvider() local_unnamed_addr #1 !dbg !1820 {
  ret i8* bitcast (i64 ()* @Kotlin_longTypeProvider to i8*), !dbg !1821
}

declare i64 @Kotlin_longTypeProvider() #2

; Function Attrs: nounwind uwtable
define linkonce_odr %"class.kotlin::RWSpinLock"* @_ZN6kotlin14ManuallyScopedINS_10RWSpinLockILNS_24MutexThreadStateHandlingE0EEELb0EE4implEv(%"class.kotlin::ManuallyScoped"* %0) #4 align 2 !dbg !1822 {
  call void @llvm.dbg.value(metadata %"class.kotlin::ManuallyScoped"* %0, metadata !1859, metadata !DIExpression()), !dbg !1861
  %2 = bitcast %"class.kotlin::ManuallyScoped"* %0 to %"class.kotlin::RWSpinLock"*, !dbg !1862
  ret %"class.kotlin::RWSpinLock"* %2, !dbg !1863
}

; Function Attrs: nounwind readnone speculatable willreturn
declare void @llvm.dbg.value(metadata, metadata, metadata) #5

; Function Attrs: norecurse nounwind readnone uwtable
define i8* @touchAllocInstance() local_unnamed_addr #1 !dbg !1864 {
  ret i8* bitcast (%struct.ObjHeader addrspace(1)* (%struct.TypeInfo*, %struct.ObjHeader addrspace(1)**)* @AllocInstance to i8*), !dbg !1865
}

; Function Attrs: nounwind
declare %struct.ObjHeader addrspace(1)* @AllocInstance(%struct.TypeInfo *, %struct.ObjHeader addrspace(1)**) #3

; Function Attrs: norecurse nounwind readnone uwtable
define i8* @touchAllocArrayInstance() local_unnamed_addr #1 !dbg !1866 {
  ret i8* bitcast (%struct.ObjHeader addrspace(1)* (%struct.TypeInfo*, i32, %struct.ObjHeader addrspace(1)**)* @AllocArrayInstance to i8*), !dbg !1867
}

declare %struct.ObjHeader addrspace(1)* @AllocArrayInstance(%struct.TypeInfo*, i32, %struct.ObjHeader addrspace(1)**) #2

; Function Attrs: norecurse nounwind readnone uwtable
define i8* @touchInitAndRegisterGlobal() local_unnamed_addr #1 !dbg !1868 {
  ret i8* bitcast (void (%struct.ObjHeader addrspace(1)**, %struct.ObjHeader addrspace(1)*)* @InitAndRegisterGlobal to i8*), !dbg !1869
}

; Function Attrs: nounwind
declare void @InitAndRegisterGlobal(%struct.ObjHeader addrspace(1)**, %struct.ObjHeader addrspace(1)*) #3

; Function Attrs: norecurse nounwind readnone uwtable
define i8* @touchUpdateHeapRef() local_unnamed_addr #1 !dbg !1870 {
  ret i8* bitcast (void (%struct.ObjHeader addrspace(1)**, %struct.ObjHeader addrspace(1)*)* @UpdateHeapRef to i8*), !dbg !1871
}

; Function Attrs: nounwind
declare void @UpdateHeapRef(%struct.ObjHeader addrspace(1)**, %struct.ObjHeader addrspace(1)*) #3

; Function Attrs: norecurse nounwind readnone uwtable
define i8* @touchUpdateStackRef() local_unnamed_addr #1 !dbg !1872 {
  ret i8* bitcast (void (%struct.ObjHeader addrspace(1)**, %struct.ObjHeader addrspace(1)*)* @UpdateStackRef to i8*), !dbg !1873
}

; Function Attrs: nounwind
declare void @UpdateStackRef(%struct.ObjHeader addrspace(1)**, %struct.ObjHeader addrspace(1)*) #3

; Function Attrs: norecurse nounwind readnone uwtable
define i8* @touchUpdateVolatileHeapRef() local_unnamed_addr #1 !dbg !1874 {
  ret i8* bitcast (void (%struct.ObjHeader addrspace(1)**, %struct.ObjHeader addrspace(1)*)* @UpdateVolatileHeapRef to i8*), !dbg !1875
}

; Function Attrs: nounwind
declare void @UpdateVolatileHeapRef(%struct.ObjHeader addrspace(1)**, %struct.ObjHeader addrspace(1)*) #3

; Function Attrs: norecurse nounwind readnone uwtable
define i8* @touchCompareAndSwapVolatileHeapRef() local_unnamed_addr #1 !dbg !1876 {
  ret i8* bitcast (%struct.ObjHeader* (%struct.ObjHeader**, %struct.ObjHeader*, %struct.ObjHeader*, %struct.ObjHeader**)* @CompareAndSwapVolatileHeapRef to i8*), !dbg !1877
}

; Function Attrs: nounwind
declare %struct.ObjHeader* @CompareAndSwapVolatileHeapRef(%struct.ObjHeader**, %struct.ObjHeader*, %struct.ObjHeader*, %struct.ObjHeader**) #3

; Function Attrs: norecurse nounwind readnone uwtable
define i8* @touchCompareAndSetVolatileHeapRef() local_unnamed_addr #1 !dbg !1878 {
  ret i8* bitcast (i1 (%struct.ObjHeader**, %struct.ObjHeader*, %struct.ObjHeader*)* @CompareAndSetVolatileHeapRef to i8*), !dbg !1879
}

; Function Attrs: nounwind
declare zeroext i1 @CompareAndSetVolatileHeapRef(%struct.ObjHeader**, %struct.ObjHeader*, %struct.ObjHeader*) #3

; Function Attrs: norecurse nounwind readnone uwtable
define i8* @touchGetAndSetVolatileHeapRef() local_unnamed_addr #1 !dbg !1880 {
  ret i8* bitcast (%struct.ObjHeader* (%struct.ObjHeader addrspace(1)**, %struct.ObjHeader addrspace(1)*, %struct.ObjHeader addrspace(1)**)* @GetAndSetVolatileHeapRef to i8*), !dbg !1881
}

; Function Attrs: nounwind
declare %struct.ObjHeader* @GetAndSetVolatileHeapRef(%struct.ObjHeader addrspace(1)**, %struct.ObjHeader addrspace(1)*, %struct.ObjHeader addrspace(1)**) #3

; Function Attrs: norecurse nounwind readnone uwtable
define i8* @touchUpdateReturnRef() local_unnamed_addr #1 !dbg !1882 {
  ret i8* bitcast (void (%struct.ObjHeader addrspace(1)**, %struct.ObjHeader addrspace(1)*)* @UpdateReturnRef to i8*), !dbg !1883
}

; Function Attrs: nounwind
declare void @UpdateReturnRef(%struct.ObjHeader addrspace(1)**, %struct.ObjHeader addrspace(1)*) #3

; Function Attrs: norecurse nounwind readnone uwtable
define i8* @touchZeroHeapRef() local_unnamed_addr #1 !dbg !1884 {
  ret i8* bitcast (void (%struct.ObjHeader addrspace(1)**)* @ZeroHeapRef to i8*), !dbg !1885
}

; Function Attrs: nounwind
declare void @ZeroHeapRef(%struct.ObjHeader addrspace(1)**) #3

; Function Attrs: norecurse nounwind readnone uwtable
define i8* @touchZeroArrayRefs() local_unnamed_addr #1 !dbg !1886 {
  ret i8* bitcast (void (%struct.ArrayHeader addrspace(1)*)* @ZeroArrayRefs to i8*), !dbg !1887
}

; Function Attrs: nounwind
declare void @ZeroArrayRefs(%struct.ArrayHeader addrspace(1)*) #3

; Function Attrs: norecurse nounwind readnone uwtable
define i8* @touchEnterFrame() local_unnamed_addr #1 !dbg !1888 {
  ret i8* bitcast (void (%struct.ObjHeader**, i32, i32)* @EnterFrame to i8*), !dbg !1889
}

; Function Attrs: nounwind
declare void @EnterFrame(%struct.ObjHeader**, i32, i32) #3

; Function Attrs: norecurse nounwind readnone uwtable
define i8* @touchLeaveFrame() local_unnamed_addr #1 !dbg !1890 {
  ret i8* bitcast (void (%struct.ObjHeader**, i32, i32)* @LeaveFrame to i8*), !dbg !1891
}

; Function Attrs: nounwind
declare void @LeaveFrame(%struct.ObjHeader**, i32, i32) #3

; Function Attrs: norecurse nounwind readnone uwtable
define i8* @touchSetCurrentFrame() local_unnamed_addr #1 !dbg !1892 {
  ret i8* bitcast (void (%struct.ObjHeader**)* @SetCurrentFrame to i8*), !dbg !1893
}

; Function Attrs: nounwind
declare void @SetCurrentFrame(%struct.ObjHeader**) #3

; Function Attrs: norecurse nounwind readnone uwtable
define i8* @touchCheckCurrentFrame() local_unnamed_addr #1 !dbg !1894 {
  ret i8* bitcast (void (%struct.ObjHeader**)* @CheckCurrentFrame to i8*), !dbg !1895
}

; Function Attrs: nounwind
declare void @CheckCurrentFrame(%struct.ObjHeader**) #3

; Function Attrs: norecurse nounwind readnone uwtable
define i8* @touchMutationCheck() local_unnamed_addr #1 !dbg !1896 {
  ret i8* bitcast (void (%struct.ObjHeader*)* @MutationCheck to i8*), !dbg !1897
}

declare void @MutationCheck(%struct.ObjHeader*) #2

; Function Attrs: norecurse nounwind readnone uwtable
define i8* @touchCheckLifetimesConstraint() local_unnamed_addr #1 !dbg !1898 {
  ret i8* bitcast (void (%struct.ObjHeader*, %struct.ObjHeader*)* @CheckLifetimesConstraint to i8*), !dbg !1899
}

; Function Attrs: nounwind
declare void @CheckLifetimesConstraint(%struct.ObjHeader*, %struct.ObjHeader*) #3

; Function Attrs: norecurse nounwind readnone uwtable
define i8* @touchFreezeSubgraph() local_unnamed_addr #1 !dbg !1900 {
  ret i8* bitcast (void (%struct.ObjHeader*)* @FreezeSubgraph to i8*), !dbg !1901
}

declare void @FreezeSubgraph(%struct.ObjHeader*) #2

; Function Attrs: norecurse nounwind readnone uwtable
define i8* @touchCheckGlobalsAccessible() local_unnamed_addr #1 !dbg !1902 {
  ret i8* bitcast (void ()* @CheckGlobalsAccessible to i8*), !dbg !1903
}

declare void @CheckGlobalsAccessible() #2

; Function Attrs: norecurse nounwind readnone uwtable
define i8* @touchLookupInterfaceTableRecord() local_unnamed_addr #1 !dbg !1904 {
  ret i8* bitcast (%struct.InterfaceTableRecord* (%struct.InterfaceTableRecord*, i32, i32)* @LookupInterfaceTableRecord to i8*), !dbg !1905
}

; Function Attrs: nounwind readnone
declare %struct.InterfaceTableRecord* @LookupInterfaceTableRecord(%struct.InterfaceTableRecord*, i32, i32) #6

; Function Attrs: norecurse nounwind readnone uwtable
define i8* @touchIsSubtype() local_unnamed_addr #1 !dbg !1906 {
  ret i8* bitcast (i1 (%struct.TypeInfo*, %struct.TypeInfo*)* @IsSubtype to i8*), !dbg !1907
}

; Function Attrs: nounwind readonly
declare zeroext i1 @IsSubtype(%struct.TypeInfo*, %struct.TypeInfo*) #7

; Function Attrs: norecurse nounwind readnone uwtable
define i8* @touchIsSubclassFast() local_unnamed_addr #1 !dbg !1908 {
  ret i8* bitcast (i1 (%struct.TypeInfo*, i32, i32)* @IsSubclassFast to i8*), !dbg !1909
}

; Function Attrs: nounwind readonly
declare zeroext i1 @IsSubclassFast(%struct.TypeInfo*, i32, i32) #7

; Function Attrs: norecurse nounwind readnone uwtable
define i8* @touchThrowException() local_unnamed_addr #1 !dbg !1910 {
  ret i8* bitcast (void (%struct.ObjHeader*)* @ThrowException to i8*), !dbg !1911
}

; Function Attrs: noreturn
declare void @ThrowException(%struct.ObjHeader*) #8

; Function Attrs: norecurse nounwind readnone uwtable
define i8* @touchKotlin_getExceptionObject() local_unnamed_addr #1 !dbg !1912 {
  ret i8* bitcast (%struct.ObjHeader* (i8*, %struct.ObjHeader**)* @Kotlin_getExceptionObject to i8*), !dbg !1913
}

; Function Attrs: nounwind
declare %struct.ObjHeader* @Kotlin_getExceptionObject(i8*, %struct.ObjHeader**) #3

; Function Attrs: norecurse nounwind readnone uwtable
define i8* @touchAppendToInitializersTail() local_unnamed_addr #1 !dbg !1914 {
  ret i8* bitcast (void (%struct.InitNode*)* @AppendToInitializersTail to i8*), !dbg !1915
}

; Function Attrs: nounwind
declare void @AppendToInitializersTail(%struct.InitNode*) #3

; Function Attrs: norecurse nounwind readnone uwtable
define i8* @touchCallInitGlobalPossiblyLock() local_unnamed_addr #1 !dbg !1916 {
  ret i8* bitcast (void (i32*, void ()*)* @CallInitGlobalPossiblyLock to i8*), !dbg !1917
}

declare void @CallInitGlobalPossiblyLock(i32*, void ()*) #2

; Function Attrs: norecurse nounwind readnone uwtable
define i8* @touchCallInitThreadLocal() local_unnamed_addr #1 !dbg !1918 {
  ret i8* bitcast (void (i32*, i32*, void ()*)* @CallInitThreadLocal to i8*), !dbg !1919
}

declare void @CallInitThreadLocal(i32*, i32*, void ()*) #2

; Function Attrs: norecurse nounwind readnone uwtable
define i8* @touchAddTLSRecord() local_unnamed_addr #1 !dbg !1920 {
  ret i8* bitcast (void (%struct.MemoryState*, i8**, i32)* @AddTLSRecord to i8*), !dbg !1921
}

; Function Attrs: nounwind
declare void @AddTLSRecord(%struct.MemoryState*, i8**, i32) #3

; Function Attrs: norecurse nounwind readnone uwtable
define i8* @touchLookupTLS() local_unnamed_addr #1 !dbg !1922 {
  ret i8* bitcast (%struct.ObjHeader** (i8**, i32)* @LookupTLS to i8*), !dbg !1923
}

; Function Attrs: nounwind
declare %struct.ObjHeader** @LookupTLS(i8**, i32) #3

; Function Attrs: norecurse nounwind readnone uwtable
define i8* @touchKotlin_initRuntimeIfNeeded() local_unnamed_addr #1 !dbg !1924 {
  ret i8* bitcast (void ()* @Kotlin_initRuntimeIfNeeded to i8*), !dbg !1925
}

; Function Attrs: nounwind
declare void @Kotlin_initRuntimeIfNeeded() #3

; Function Attrs: norecurse nounwind readnone uwtable
define i8* @touchKRefSharedHolder_initLocal() local_unnamed_addr #1 !dbg !1926 {
  ret i8* bitcast (void (%class.KRefSharedHolder*, %struct.ObjHeader*)* @KRefSharedHolder_initLocal to i8*), !dbg !1927
}

; Function Attrs: nounwind
declare void @KRefSharedHolder_initLocal(%class.KRefSharedHolder*, %struct.ObjHeader*) #3

; Function Attrs: norecurse nounwind readnone uwtable
define i8* @touchKRefSharedHolder_init() local_unnamed_addr #1 !dbg !1928 {
  ret i8* bitcast (void (%class.KRefSharedHolder*, %struct.ObjHeader*)* @KRefSharedHolder_init to i8*), !dbg !1929
}

; Function Attrs: nounwind
declare void @KRefSharedHolder_init(%class.KRefSharedHolder*, %struct.ObjHeader*) #3

; Function Attrs: norecurse nounwind readnone uwtable
define i8* @touchKRefSharedHolder_dispose() local_unnamed_addr #1 !dbg !1930 {
  ret i8* bitcast (void (%class.KRefSharedHolder*)* @KRefSharedHolder_dispose to i8*), !dbg !1931
}

; Function Attrs: nounwind
declare void @KRefSharedHolder_dispose(%class.KRefSharedHolder*) #3

; Function Attrs: norecurse nounwind readnone uwtable
define i8* @touchKRefSharedHolder_ref() local_unnamed_addr #1 !dbg !1932 {
  ret i8* bitcast (%struct.ObjHeader* (%class.KRefSharedHolder*)* @KRefSharedHolder_ref to i8*), !dbg !1933
}

; Function Attrs: nounwind
declare %struct.ObjHeader* @KRefSharedHolder_ref(%class.KRefSharedHolder*) #3

; Function Attrs: norecurse nounwind readnone uwtable
define i8* @touchKotlin_mm_switchThreadStateNative() local_unnamed_addr #1 !dbg !1934 {
  ret i8* bitcast (void ()* @Kotlin_mm_switchThreadStateNative to i8*), !dbg !1935
}

; Function Attrs: nounwind
declare void @Kotlin_mm_switchThreadStateNative() #3

; Function Attrs: norecurse nounwind readnone uwtable
define i8* @touchKotlin_mm_switchThreadStateNative_debug() local_unnamed_addr #1 !dbg !1936 {
  ret i8* bitcast (void ()* @Kotlin_mm_switchThreadStateNative_debug to i8*), !dbg !1937
}

; Function Attrs: nounwind
declare void @Kotlin_mm_switchThreadStateNative_debug() #3

; Function Attrs: norecurse nounwind readnone uwtable
define i8* @touchKotlin_mm_switchThreadStateRunnable() local_unnamed_addr #1 !dbg !1938 {
  ret i8* bitcast (void ()* @Kotlin_mm_switchThreadStateRunnable to i8*), !dbg !1939
}

; Function Attrs: nounwind
declare void @Kotlin_mm_switchThreadStateRunnable() #3

; Function Attrs: norecurse nounwind readnone uwtable
define i8* @touchKotlin_mm_switchThreadStateRunnable_debug() local_unnamed_addr #1 !dbg !1940 {
  ret i8* bitcast (void ()* @Kotlin_mm_switchThreadStateRunnable_debug to i8*), !dbg !1941
}

; Function Attrs: nounwind
declare void @Kotlin_mm_switchThreadStateRunnable_debug() #3

; Function Attrs: norecurse nounwind readnone uwtable
define i8* @touchKotlin_mm_safePointFunctionPrologue() local_unnamed_addr #1 !dbg !1942 {
  ret i8* bitcast (void ()* @Kotlin_mm_safePointFunctionPrologue to i8*), !dbg !1943
}

; Function Attrs: nounwind
declare void @Kotlin_mm_safePointFunctionPrologue() #3

; Function Attrs: norecurse nounwind readnone uwtable
define i8* @touchKotlin_mm_safePointWhileLoopBody() local_unnamed_addr #1 !dbg !1944 {
  ret i8* bitcast (void ()* @Kotlin_mm_safePointWhileLoopBody to i8*), !dbg !1945
}

; Function Attrs: nounwind
declare void @Kotlin_mm_safePointWhileLoopBody() #3

; Function Attrs: norecurse nounwind readnone uwtable
define i8* @touchKotlin_processObjectInMark() local_unnamed_addr #1 !dbg !1946 {
  ret i8* bitcast (void (i8*, %struct.ObjHeader addrspace(1)*)* @Kotlin_processObjectInMark to i8*), !dbg !1947
}

; Function Attrs: nounwind
declare void @Kotlin_processObjectInMark(i8*, %struct.ObjHeader addrspace(1)*) #3

; Function Attrs: norecurse nounwind readnone uwtable
define i8* @touchKotlin_processArrayInMark() local_unnamed_addr #1 !dbg !1948 {
  ret i8* bitcast (void (i8*, %struct.ObjHeader addrspace(1)*)* @Kotlin_processArrayInMark to i8*), !dbg !1949
}

; Function Attrs: nounwind
declare void @Kotlin_processArrayInMark(i8*, %struct.ObjHeader addrspace(1)*) #3

; Function Attrs: norecurse nounwind readnone uwtable
define i8* @touchKotlin_processEmptyObjectInMark() local_unnamed_addr #1 !dbg !1950 {
  ret i8* bitcast (void (i8*, %struct.ObjHeader addrspace(1)*)* @Kotlin_processEmptyObjectInMark to i8*), !dbg !1951
}

; Function Attrs: nounwind
declare void @Kotlin_processEmptyObjectInMark(i8*, %struct.ObjHeader addrspace(1)*) #3

; Function Attrs: norecurse nounwind readnone uwtable
define i8* @touchKotlin_arrayGetElementAddress() local_unnamed_addr #1 !dbg !1952 {
  ret i8* bitcast (%struct.ObjHeader** (%struct.ObjHeader*, i32)* @Kotlin_arrayGetElementAddress to i8*), !dbg !1953
}

; Function Attrs: nounwind readonly
declare %struct.ObjHeader** @Kotlin_arrayGetElementAddress(%struct.ObjHeader*, i32) #7

; Function Attrs: norecurse nounwind readnone uwtable
define i8* @touchKotlin_intArrayGetElementAddress() local_unnamed_addr #1 !dbg !1954 {
  ret i8* bitcast (i32* (%struct.ObjHeader*, i32)* @Kotlin_intArrayGetElementAddress to i8*), !dbg !1955
}

; Function Attrs: nounwind readonly
declare i32* @Kotlin_intArrayGetElementAddress(%struct.ObjHeader*, i32) #7

; Function Attrs: norecurse nounwind readnone uwtable
define i8* @touchKotlin_longArrayGetElementAddress() local_unnamed_addr #1 !dbg !1956 {
  ret i8* bitcast (i64* (%struct.ObjHeader*, i32)* @Kotlin_longArrayGetElementAddress to i8*), !dbg !1957
}

; Function Attrs: nounwind readonly
declare i64* @Kotlin_longArrayGetElementAddress(%struct.ObjHeader*, i32) #7

; Function Attrs: norecurse nounwind readnone uwtable
define i8* @touchSaveThreadLastKotlinFrame2() local_unnamed_addr #1 {
  ret i8* bitcast (void ()* @SaveThreadLastKotlinFrame2 to i8*)
}

; Function Attrs: nounwind
declare void @SaveThreadLastKotlinFrame2() #3

; Function Attrs: norecurse nounwind readnone uwtable
define i8* @touchRestoreThreadLastKotlinFrame2() local_unnamed_addr #1 {
  ret i8* bitcast (void ()* @RestoreThreadLastKotlinFrame2 to i8*)
}

; Function Attrs: nounwind
declare void @RestoreThreadLastKotlinFrame2() #3

attributes #0 = { uwtable "correctly-rounded-divide-sqrt-fp-math"="false" "disable-tail-calls"="false" "frame-pointer"="non-leaf" "less-precise-fpmad"="false" "min-legal-vector-width"="0" "no-infs-fp-math"="false" "no-jump-tables"="false" "no-nans-fp-math"="false" "no-signed-zeros-fp-math"="false" "no-trapping-math"="true" "stack-protector-buffer-size"="8" "target-cpu"="apple-a7" "target-features"="+aes,+crypto,+fp-armv8,+neon,+sha2,+zcm,+zcz" "unsafe-fp-math"="false" "use-soft-float"="false" }
attributes #1 = { norecurse nounwind readnone uwtable "correctly-rounded-divide-sqrt-fp-math"="false" "disable-tail-calls"="false" "frame-pointer"="non-leaf" "less-precise-fpmad"="false" "min-legal-vector-width"="0" "no-infs-fp-math"="false" "no-jump-tables"="false" "no-nans-fp-math"="false" "no-signed-zeros-fp-math"="false" "no-trapping-math"="true" "stack-protector-buffer-size"="8" "target-cpu"="apple-a7" "target-features"="+aes,+crypto,+fp-armv8,+neon,+sha2,+zcm,+zcz" "unsafe-fp-math"="false" "use-soft-float"="false" }
attributes #2 = { "correctly-rounded-divide-sqrt-fp-math"="false" "disable-tail-calls"="false" "frame-pointer"="non-leaf" "less-precise-fpmad"="false" "no-infs-fp-math"="false" "no-nans-fp-math"="false" "no-signed-zeros-fp-math"="false" "no-trapping-math"="true" "stack-protector-buffer-size"="8" "target-cpu"="apple-a7" "target-features"="+aes,+crypto,+fp-armv8,+neon,+sha2,+zcm,+zcz" "unsafe-fp-math"="false" "use-soft-float"="false" }
attributes #3 = { nounwind "correctly-rounded-divide-sqrt-fp-math"="false" "disable-tail-calls"="false" "frame-pointer"="non-leaf" "less-precise-fpmad"="false" "no-infs-fp-math"="false" "no-nans-fp-math"="false" "no-signed-zeros-fp-math"="false" "no-trapping-math"="true" "stack-protector-buffer-size"="8" "target-cpu"="apple-a7" "target-features"="+aes,+crypto,+fp-armv8,+neon,+sha2,+zcm,+zcz" "unsafe-fp-math"="false" "use-soft-float"="false" }
attributes #4 = { nounwind uwtable "correctly-rounded-divide-sqrt-fp-math"="false" "disable-tail-calls"="false" "frame-pointer"="non-leaf" "less-precise-fpmad"="false" "min-legal-vector-width"="0" "no-infs-fp-math"="false" "no-jump-tables"="false" "no-nans-fp-math"="false" "no-signed-zeros-fp-math"="false" "no-trapping-math"="true" "stack-protector-buffer-size"="8" "target-cpu"="apple-a7" "target-features"="+aes,+crypto,+fp-armv8,+neon,+sha2,+zcm,+zcz" "unsafe-fp-math"="false" "use-soft-float"="false" }
attributes #5 = { nounwind readnone speculatable willreturn }
attributes #6 = { nounwind readnone "correctly-rounded-divide-sqrt-fp-math"="false" "disable-tail-calls"="false" "frame-pointer"="non-leaf" "less-precise-fpmad"="false" "no-infs-fp-math"="false" "no-nans-fp-math"="false" "no-signed-zeros-fp-math"="false" "no-trapping-math"="true" "stack-protector-buffer-size"="8" "target-cpu"="apple-a7" "target-features"="+aes,+crypto,+fp-armv8,+neon,+sha2,+zcm,+zcz" "unsafe-fp-math"="false" "use-soft-float"="false" }
attributes #7 = { nounwind readonly "correctly-rounded-divide-sqrt-fp-math"="false" "disable-tail-calls"="false" "frame-pointer"="non-leaf" "less-precise-fpmad"="false" "no-infs-fp-math"="false" "no-nans-fp-math"="false" "no-signed-zeros-fp-math"="false" "no-trapping-math"="true" "stack-protector-buffer-size"="8" "target-cpu"="apple-a7" "target-features"="+aes,+crypto,+fp-armv8,+neon,+sha2,+zcm,+zcz" "unsafe-fp-math"="false" "use-soft-float"="false" }
attributes #8 = { noreturn "correctly-rounded-divide-sqrt-fp-math"="false" "disable-tail-calls"="false" "frame-pointer"="non-leaf" "less-precise-fpmad"="false" "no-infs-fp-math"="false" "no-nans-fp-math"="false" "no-signed-zeros-fp-math"="false" "no-trapping-math"="true" "stack-protector-buffer-size"="8" "target-cpu"="apple-a7" "target-features"="+aes,+crypto,+fp-armv8,+neon,+sha2,+zcm,+zcz" "unsafe-fp-math"="false" "use-soft-float"="false" }

!llvm.dbg.cu = !{!2, !939}
!llvm.ident = !{!1749, !1749}
!llvm.module.flags = !{!1750, !1751, !1752, !1753, !1754, !1755, !1756, !1757, !1758, !1759}

!0 = !DIGlobalVariableExpression(var: !1, expr: !DIExpression())
!1 = distinct !DIGlobalVariable(name: "touchWritableTypeInfo", scope: !2, file: !10, line: 20, type: !160, isLocal: false, isDefinition: true)
!2 = distinct !DICompileUnit(language: DW_LANG_ObjC_plus_plus, file: !3, producer: "clang version 11.1.0 (https://github.com/apple/llvm-project 9205ffc7869a87cf3906b80dbd45b969c5794ef7)", isOptimized: true, runtimeVersion: 2, emissionKind: FullDebug, enums: !4, retainedTypes: !5, globals: !7, imports: !319, nameTableKind: None, sysroot: "/Applications/Xcode_16.app/Contents/Developer/Platforms/MacOSX.platform/Developer/SDKs/MacOSX15.0.sdk", sdk: "MacOSX15.0.sdk")
!3 = !DIFile(filename: "/Users/bytedance/KMP/kt2/kotlin_2.0/kotlin-native/runtime/src/compiler_interface/cpp/CompilerObjCInterface.mm", directory: "/Users/bytedance/KMP/kt2/kotlin_2.0/kotlin-native/runtime/src")
!4 = !{}
!5 = !{!6}
!6 = !DIDerivedType(tag: DW_TAG_pointer_type, baseType: null, size: 64)
!7 = !{!0, !8, !247, !279, !281, !283, !285, !287, !289, !317}
!8 = !DIGlobalVariableExpression(var: !9, expr: !DIExpression())
!9 = distinct !DIGlobalVariable(name: "touchKotlinObjCClassData", scope: !2, file: !10, line: 21, type: !11, isLocal: false, isDefinition: true)
!10 = !DIFile(filename: "compiler_interface/cpp/CompilerObjCInterface.mm", directory: "/Users/bytedance/KMP/kt2/kotlin_2.0/kotlin-native/runtime/src")
!11 = distinct !DICompositeType(tag: DW_TAG_structure_type, name: "KotlinObjCClassData", file: !12, line: 21, size: 192, flags: DIFlagTypePassByValue, elements: !13, identifier: "_ZTS19KotlinObjCClassData")
!12 = !DIFile(filename: "main/cpp/ObjCInterop.h", directory: "/Users/bytedance/KMP/kt2/kotlin_2.0/kotlin-native/runtime/src")
!13 = !{!14, !245, !246}
!14 = !DIDerivedType(tag: DW_TAG_member, name: "typeInfo", scope: !11, file: !12, line: 22, baseType: !15, size: 64)
!15 = !DIDerivedType(tag: DW_TAG_pointer_type, baseType: !16, size: 64)
!16 = !DIDerivedType(tag: DW_TAG_const_type, baseType: !17)
!17 = distinct !DICompositeType(tag: DW_TAG_structure_type, name: "TypeInfo", file: !18, line: 97, size: 1024, flags: DIFlagTypePassByValue, elements: !19, identifier: "_ZTS8TypeInfo")
!18 = !DIFile(filename: "main/cpp/TypeInfo.h", directory: "/Users/bytedance/KMP/kt2/kotlin_2.0/kotlin-native/runtime/src")
!19 = !{!20, !21, !47, !51, !52, !53, !54, !55, !58, !59, !60, !74, !155, !156, !157, !158, !214, !225, !229, !230, !234, !239, !242}
!20 = !DIDerivedType(tag: DW_TAG_member, name: "typeInfo_", scope: !17, file: !18, line: 99, baseType: !15, size: 64)
!21 = !DIDerivedType(tag: DW_TAG_member, name: "extendedInfo_", scope: !17, file: !18, line: 101, baseType: !22, size: 64, offset: 64)
!22 = !DIDerivedType(tag: DW_TAG_pointer_type, baseType: !23, size: 64)
!23 = !DIDerivedType(tag: DW_TAG_const_type, baseType: !24)
!24 = distinct !DICompositeType(tag: DW_TAG_structure_type, name: "ExtendedTypeInfo", file: !18, line: 64, size: 384, flags: DIFlagTypePassByValue, elements: !25, identifier: "_ZTS16ExtendedTypeInfo")
!25 = !{!26, !30, !33, !39, !44, !45}
!26 = !DIDerivedType(tag: DW_TAG_member, name: "fieldsCount_", scope: !24, file: !18, line: 66, baseType: !27, size: 32)
!27 = !DIDerivedType(tag: DW_TAG_typedef, name: "int32_t", file: !28, line: 30, baseType: !29)
!28 = !DIFile(filename: "/Applications/Xcode_16.app/Contents/Developer/Platforms/MacOSX.platform/Developer/SDKs/MacOSX15.0.sdk/usr/include/sys/_types/_int32_t.h", directory: "")
!29 = !DIBasicType(name: "int", size: 32, encoding: DW_ATE_signed)
!30 = !DIDerivedType(tag: DW_TAG_member, name: "fieldOffsets_", scope: !24, file: !18, line: 68, baseType: !31, size: 64, offset: 64)
!31 = !DIDerivedType(tag: DW_TAG_pointer_type, baseType: !32, size: 64)
!32 = !DIDerivedType(tag: DW_TAG_const_type, baseType: !27)
!33 = !DIDerivedType(tag: DW_TAG_member, name: "fieldTypes_", scope: !24, file: !18, line: 70, baseType: !34, size: 64, offset: 128)
!34 = !DIDerivedType(tag: DW_TAG_pointer_type, baseType: !35, size: 64)
!35 = !DIDerivedType(tag: DW_TAG_const_type, baseType: !36)
!36 = !DIDerivedType(tag: DW_TAG_typedef, name: "uint8_t", file: !37, line: 31, baseType: !38)
!37 = !DIFile(filename: "/Applications/Xcode_16.app/Contents/Developer/Platforms/MacOSX.platform/Developer/SDKs/MacOSX15.0.sdk/usr/include/_types/_uint8_t.h", directory: "")
!38 = !DIBasicType(name: "unsigned char", size: 8, encoding: DW_ATE_unsigned_char)
!39 = !DIDerivedType(tag: DW_TAG_member, name: "fieldNames_", scope: !24, file: !18, line: 72, baseType: !40, size: 64, offset: 192)
!40 = !DIDerivedType(tag: DW_TAG_pointer_type, baseType: !41, size: 64)
!41 = !DIDerivedType(tag: DW_TAG_pointer_type, baseType: !42, size: 64)
!42 = !DIDerivedType(tag: DW_TAG_const_type, baseType: !43)
!43 = !DIBasicType(name: "char", size: 8, encoding: DW_ATE_signed_char)
!44 = !DIDerivedType(tag: DW_TAG_member, name: "debugOperationsCount_", scope: !24, file: !18, line: 74, baseType: !27, size: 32, offset: 256)
!45 = !DIDerivedType(tag: DW_TAG_member, name: "debugOperations_", scope: !24, file: !18, line: 76, baseType: !46, size: 64, offset: 320)
!46 = !DIDerivedType(tag: DW_TAG_pointer_type, baseType: !6, size: 64)
!47 = !DIDerivedType(tag: DW_TAG_member, name: "unused_", scope: !17, file: !18, line: 103, baseType: !48, size: 32, offset: 128)
!48 = !DIDerivedType(tag: DW_TAG_typedef, name: "uint32_t", file: !49, line: 31, baseType: !50)
!49 = !DIFile(filename: "/Applications/Xcode_16.app/Contents/Developer/Platforms/MacOSX.platform/Developer/SDKs/MacOSX15.0.sdk/usr/include/_types/_uint32_t.h", directory: "")
!50 = !DIBasicType(name: "unsigned int", size: 32, encoding: DW_ATE_unsigned)
!51 = !DIDerivedType(tag: DW_TAG_member, name: "instanceSize_", scope: !17, file: !18, line: 105, baseType: !27, size: 32, offset: 160)
!52 = !DIDerivedType(tag: DW_TAG_member, name: "superType_", scope: !17, file: !18, line: 107, baseType: !15, size: 64, offset: 192)
!53 = !DIDerivedType(tag: DW_TAG_member, name: "objOffsets_", scope: !17, file: !18, line: 109, baseType: !31, size: 64, offset: 256)
!54 = !DIDerivedType(tag: DW_TAG_member, name: "objOffsetsCount_", scope: !17, file: !18, line: 112, baseType: !27, size: 32, offset: 320)
!55 = !DIDerivedType(tag: DW_TAG_member, name: "implementedInterfaces_", scope: !17, file: !18, line: 113, baseType: !56, size: 64, offset: 384)
!56 = !DIDerivedType(tag: DW_TAG_pointer_type, baseType: !57, size: 64)
!57 = !DIDerivedType(tag: DW_TAG_const_type, baseType: !15)
!58 = !DIDerivedType(tag: DW_TAG_member, name: "implementedInterfacesCount_", scope: !17, file: !18, line: 114, baseType: !27, size: 32, offset: 448)
!59 = !DIDerivedType(tag: DW_TAG_member, name: "interfaceTableSize_", scope: !17, file: !18, line: 115, baseType: !27, size: 32, offset: 480)
!60 = !DIDerivedType(tag: DW_TAG_member, name: "interfaceTable_", scope: !17, file: !18, line: 116, baseType: !61, size: 64, offset: 512)
!61 = !DIDerivedType(tag: DW_TAG_pointer_type, baseType: !62, size: 64)
!62 = !DIDerivedType(tag: DW_TAG_const_type, baseType: !63)
!63 = distinct !DICompositeType(tag: DW_TAG_structure_type, name: "InterfaceTableRecord", file: !18, line: 85, size: 128, flags: DIFlagTypePassByValue, elements: !64, identifier: "_ZTS20InterfaceTableRecord")
!64 = !{!65, !67, !68}
!65 = !DIDerivedType(tag: DW_TAG_member, name: "id", scope: !63, file: !18, line: 86, baseType: !66, size: 32)
!66 = !DIDerivedType(tag: DW_TAG_typedef, name: "ClassId", file: !18, line: 81, baseType: !27)
!67 = !DIDerivedType(tag: DW_TAG_member, name: "vtableSize", scope: !63, file: !18, line: 87, baseType: !48, size: 32, offset: 32)
!68 = !DIDerivedType(tag: DW_TAG_member, name: "vtable", scope: !63, file: !18, line: 88, baseType: !69, size: 64, offset: 64)
!69 = !DIDerivedType(tag: DW_TAG_pointer_type, baseType: !70, size: 64)
!70 = !DIDerivedType(tag: DW_TAG_const_type, baseType: !71)
!71 = !DIDerivedType(tag: DW_TAG_typedef, name: "VTableElement", file: !18, line: 79, baseType: !72)
!72 = !DIDerivedType(tag: DW_TAG_pointer_type, baseType: !73, size: 64)
!73 = !DIDerivedType(tag: DW_TAG_const_type, baseType: null)
!74 = !DIDerivedType(tag: DW_TAG_member, name: "packageName_", scope: !17, file: !18, line: 119, baseType: !75, size: 64, offset: 576)
!75 = !DIDerivedType(tag: DW_TAG_pointer_type, baseType: !76, size: 64)
!76 = distinct !DICompositeType(tag: DW_TAG_structure_type, name: "ObjHeader", file: !77, line: 50, size: 64, flags: DIFlagTypePassByValue, elements: !78, identifier: "_ZTS9ObjHeader")
!77 = !DIFile(filename: "main/cpp/Memory.h", directory: "/Users/bytedance/KMP/kt2/kotlin_2.0/kotlin-native/runtime/src")
!78 = !{!79, !81, !86, !91, !92, !95, !99, !103, !106, !109, !112, !115, !118, !121, !122, !143, !147, !148, !149, !152}
!79 = !DIDerivedType(tag: DW_TAG_member, name: "typeInfoOrMeta_", scope: !76, file: !77, line: 51, baseType: !80, size: 64)
!80 = !DIDerivedType(tag: DW_TAG_pointer_type, baseType: !17, size: 64)
!81 = !DISubprogram(name: "AsMetaObject", linkageName: "_ZN9ObjHeader12AsMetaObjectEP8TypeInfo", scope: !76, file: !77, line: 54, type: !82, scopeLine: 54, flags: DIFlagPrototyped | DIFlagStaticMember, spFlags: DISPFlagOptimized)
!82 = !DISubroutineType(types: !83)
!83 = !{!84, !80}
!84 = !DIDerivedType(tag: DW_TAG_pointer_type, baseType: !85, size: 64)
!85 = distinct !DICompositeType(tag: DW_TAG_structure_type, name: "MetaObjHeader", file: !77, line: 47, flags: DIFlagFwdDecl | DIFlagNonTrivial, identifier: "_ZTS13MetaObjHeader")
!86 = !DISubprogram(name: "typeInfoOrMetaRelaxed", linkageName: "_ZNK9ObjHeader21typeInfoOrMetaRelaxedEv", scope: !76, file: !77, line: 63, type: !87, scopeLine: 63, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!87 = !DISubroutineType(types: !88)
!88 = !{!80, !89}
!89 = !DIDerivedType(tag: DW_TAG_pointer_type, baseType: !90, size: 64, flags: DIFlagArtificial | DIFlagObjectPointer)
!90 = !DIDerivedType(tag: DW_TAG_const_type, baseType: !76)
!91 = !DISubprogram(name: "typeInfoOrMetaAcquire", linkageName: "_ZNK9ObjHeader21typeInfoOrMetaAcquireEv", scope: !76, file: !77, line: 64, type: !87, scopeLine: 64, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!92 = !DISubprogram(name: "type_info", linkageName: "_ZNK9ObjHeader9type_infoEv", scope: !76, file: !77, line: 78, type: !93, scopeLine: 78, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!93 = !DISubroutineType(types: !94)
!94 = !{!15, !89}
!95 = !DISubprogram(name: "has_meta_object", linkageName: "_ZNK9ObjHeader15has_meta_objectEv", scope: !76, file: !77, line: 85, type: !96, scopeLine: 85, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!96 = !DISubroutineType(types: !97)
!97 = !{!98, !89}
!98 = !DIBasicType(name: "bool", size: 8, encoding: DW_ATE_boolean)
!99 = !DISubprogram(name: "meta_object", linkageName: "_ZN9ObjHeader11meta_objectEv", scope: !76, file: !77, line: 89, type: !100, scopeLine: 89, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!100 = !DISubroutineType(types: !101)
!101 = !{!84, !102}
!102 = !DIDerivedType(tag: DW_TAG_pointer_type, baseType: !76, size: 64, flags: DIFlagArtificial | DIFlagObjectPointer)
!103 = !DISubprogram(name: "meta_object_or_null", linkageName: "_ZNK9ObjHeader19meta_object_or_nullEv", scope: !76, file: !77, line: 96, type: !104, scopeLine: 96, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!104 = !DISubroutineType(types: !105)
!105 = !{!84, !89}
!106 = !DISubprogram(name: "GetWeakCounter", linkageName: "_ZN9ObjHeader14GetWeakCounterEv", scope: !76, file: !77, line: 98, type: !107, scopeLine: 98, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!107 = !DISubroutineType(types: !108)
!108 = !{!75, !102}
!109 = !DISubprogram(name: "GetOrSetWeakCounter", linkageName: "_ZN9ObjHeader19GetOrSetWeakCounterEPS_", scope: !76, file: !77, line: 99, type: !110, scopeLine: 99, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!110 = !DISubroutineType(types: !111)
!111 = !{!75, !102, !75}
!112 = !DISubprogram(name: "GetAssociatedObject", linkageName: "_ZNK9ObjHeader19GetAssociatedObjectEv", scope: !76, file: !77, line: 103, type: !113, scopeLine: 103, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!113 = !DISubroutineType(types: !114)
!114 = !{!6, !89}
!115 = !DISubprogram(name: "SetAssociatedObject", linkageName: "_ZN9ObjHeader19SetAssociatedObjectEPv", scope: !76, file: !77, line: 104, type: !116, scopeLine: 104, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!116 = !DISubroutineType(types: !117)
!117 = !{null, !102, !6}
!118 = !DISubprogram(name: "CasAssociatedObject", linkageName: "_ZN9ObjHeader19CasAssociatedObjectEPvS0_", scope: !76, file: !77, line: 105, type: !119, scopeLine: 105, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!119 = !DISubroutineType(types: !120)
!120 = !{!6, !102, !6, !6}
!121 = !DISubprogram(name: "local", linkageName: "_ZNK9ObjHeader5localEv", scope: !76, file: !77, line: 108, type: !96, scopeLine: 108, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!122 = !DISubprogram(name: "array", linkageName: "_ZN9ObjHeader5arrayEv", scope: !76, file: !77, line: 116, type: !123, scopeLine: 116, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!123 = !DISubroutineType(types: !124)
!124 = !{!125, !102}
!125 = !DIDerivedType(tag: DW_TAG_pointer_type, baseType: !126, size: 64)
!126 = distinct !DICompositeType(tag: DW_TAG_structure_type, name: "ArrayHeader", file: !77, line: 131, size: 128, flags: DIFlagTypePassByValue, elements: !127, identifier: "_ZTS11ArrayHeader")
!127 = !{!128, !129, !130, !135, !139}
!128 = !DIDerivedType(tag: DW_TAG_member, name: "typeInfoOrMeta_", scope: !126, file: !77, line: 132, baseType: !80, size: 64)
!129 = !DIDerivedType(tag: DW_TAG_member, name: "count_", scope: !126, file: !77, line: 142, baseType: !48, size: 32, offset: 64)
!130 = !DISubprogram(name: "type_info", linkageName: "_ZNK11ArrayHeader9type_infoEv", scope: !126, file: !77, line: 134, type: !131, scopeLine: 134, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!131 = !DISubroutineType(types: !132)
!132 = !{!15, !133}
!133 = !DIDerivedType(tag: DW_TAG_pointer_type, baseType: !134, size: 64, flags: DIFlagArtificial | DIFlagObjectPointer)
!134 = !DIDerivedType(tag: DW_TAG_const_type, baseType: !126)
!135 = !DISubprogram(name: "obj", linkageName: "_ZN11ArrayHeader3objEv", scope: !126, file: !77, line: 138, type: !136, scopeLine: 138, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!136 = !DISubroutineType(types: !137)
!137 = !{!75, !138}
!138 = !DIDerivedType(tag: DW_TAG_pointer_type, baseType: !126, size: 64, flags: DIFlagArtificial | DIFlagObjectPointer)
!139 = !DISubprogram(name: "obj", linkageName: "_ZNK11ArrayHeader3objEv", scope: !126, file: !77, line: 139, type: !140, scopeLine: 139, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!140 = !DISubroutineType(types: !141)
!141 = !{!142, !133}
!142 = !DIDerivedType(tag: DW_TAG_pointer_type, baseType: !90, size: 64)
!143 = !DISubprogram(name: "array", linkageName: "_ZNK9ObjHeader5arrayEv", scope: !76, file: !77, line: 117, type: !144, scopeLine: 117, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!144 = !DISubroutineType(types: !145)
!145 = !{!146, !89}
!146 = !DIDerivedType(tag: DW_TAG_pointer_type, baseType: !134, size: 64)
!147 = !DISubprogram(name: "permanent", linkageName: "_ZNK9ObjHeader9permanentEv", scope: !76, file: !77, line: 119, type: !96, scopeLine: 119, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!148 = !DISubprogram(name: "heap", linkageName: "_ZNK9ObjHeader4heapEv", scope: !76, file: !77, line: 123, type: !96, scopeLine: 123, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!149 = !DISubprogram(name: "createMetaObject", linkageName: "_ZN9ObjHeader16createMetaObjectEPS_", scope: !76, file: !77, line: 125, type: !150, scopeLine: 125, flags: DIFlagPrototyped | DIFlagStaticMember, spFlags: DISPFlagOptimized)
!150 = !DISubroutineType(types: !151)
!151 = !{!84, !75}
!152 = !DISubprogram(name: "destroyMetaObject", linkageName: "_ZN9ObjHeader17destroyMetaObjectEPS_", scope: !76, file: !77, line: 126, type: !153, scopeLine: 126, flags: DIFlagPrototyped | DIFlagStaticMember, spFlags: DISPFlagOptimized)
!153 = !DISubroutineType(types: !154)
!154 = !{null, !75}
!155 = !DIDerivedType(tag: DW_TAG_member, name: "relativeName_", scope: !17, file: !18, line: 124, baseType: !75, size: 64, offset: 640)
!156 = !DIDerivedType(tag: DW_TAG_member, name: "flags_", scope: !17, file: !18, line: 127, baseType: !27, size: 32, offset: 704)
!157 = !DIDerivedType(tag: DW_TAG_member, name: "classId_", scope: !17, file: !18, line: 130, baseType: !66, size: 32, offset: 736)
!158 = !DIDerivedType(tag: DW_TAG_member, name: "writableInfo_", scope: !17, file: !18, line: 133, baseType: !159, size: 64, offset: 768)
!159 = !DIDerivedType(tag: DW_TAG_pointer_type, baseType: !160, size: 64)
!160 = distinct !DICompositeType(tag: DW_TAG_structure_type, name: "WritableTypeInfo", file: !161, line: 64, size: 192, flags: DIFlagTypePassByValue, elements: !162, identifier: "_ZTS16WritableTypeInfo")
!161 = !DIFile(filename: "main/cpp/ObjCExport.h", directory: "/Users/bytedance/KMP/kt2/kotlin_2.0/kotlin-native/runtime/src")
!162 = !{!163}
!163 = !DIDerivedType(tag: DW_TAG_member, name: "objCExport", scope: !160, file: !161, line: 65, baseType: !164, size: 192)
!164 = distinct !DICompositeType(tag: DW_TAG_structure_type, name: "TypeInfoObjCExportAddition", file: !161, line: 58, size: 192, flags: DIFlagTypePassByValue, elements: !165, identifier: "_ZTS26TypeInfoObjCExportAddition")
!165 = !{!166, !167, !171}
!166 = !DIDerivedType(tag: DW_TAG_member, name: "convertToRetained", scope: !164, file: !161, line: 59, baseType: !6, size: 64)
!167 = !DIDerivedType(tag: DW_TAG_member, name: "objCClass", scope: !164, file: !161, line: 60, baseType: !168, size: 64, offset: 64)
!168 = !DIDerivedType(tag: DW_TAG_typedef, name: "Class", file: !3, line: 20, baseType: !169)
!169 = !DIDerivedType(tag: DW_TAG_pointer_type, baseType: !170, size: 64)
!170 = !DICompositeType(tag: DW_TAG_structure_type, name: "objc_class", file: !3, flags: DIFlagFwdDecl)
!171 = !DIDerivedType(tag: DW_TAG_member, name: "typeAdapter", scope: !164, file: !161, line: 61, baseType: !172, size: 64, offset: 128)
!172 = !DIDerivedType(tag: DW_TAG_pointer_type, baseType: !173, size: 64)
!173 = !DIDerivedType(tag: DW_TAG_const_type, baseType: !174)
!174 = distinct !DICompositeType(tag: DW_TAG_structure_type, name: "ObjCTypeAdapter", file: !161, line: 34, size: 896, flags: DIFlagTypePassByValue, elements: !175, identifier: "_ZTS15ObjCTypeAdapter")
!175 = !{!176, !177, !180, !181, !182, !183, !184, !197, !198, !199, !200, !201, !202, !213}
!176 = !DIDerivedType(tag: DW_TAG_member, name: "kotlinTypeInfo", scope: !174, file: !161, line: 35, baseType: !15, size: 64)
!177 = !DIDerivedType(tag: DW_TAG_member, name: "kotlinVtable", scope: !174, file: !161, line: 37, baseType: !178, size: 64, offset: 64)
!178 = !DIDerivedType(tag: DW_TAG_pointer_type, baseType: !179, size: 64)
!179 = !DIDerivedType(tag: DW_TAG_const_type, baseType: !72)
!180 = !DIDerivedType(tag: DW_TAG_member, name: "kotlinVtableSize", scope: !174, file: !161, line: 38, baseType: !29, size: 32, offset: 128)
!181 = !DIDerivedType(tag: DW_TAG_member, name: "kotlinItable", scope: !174, file: !161, line: 40, baseType: !61, size: 64, offset: 192)
!182 = !DIDerivedType(tag: DW_TAG_member, name: "kotlinItableSize", scope: !174, file: !161, line: 41, baseType: !29, size: 32, offset: 256)
!183 = !DIDerivedType(tag: DW_TAG_member, name: "objCName", scope: !174, file: !161, line: 43, baseType: !41, size: 64, offset: 320)
!184 = !DIDerivedType(tag: DW_TAG_member, name: "directAdapters", scope: !174, file: !161, line: 45, baseType: !185, size: 64, offset: 384)
!185 = !DIDerivedType(tag: DW_TAG_pointer_type, baseType: !186, size: 64)
!186 = !DIDerivedType(tag: DW_TAG_const_type, baseType: !187)
!187 = distinct !DICompositeType(tag: DW_TAG_structure_type, name: "ObjCToKotlinMethodAdapter", file: !161, line: 19, size: 192, flags: DIFlagTypePassByValue, elements: !188, identifier: "_ZTS25ObjCToKotlinMethodAdapter")
!188 = !{!189, !190, !191}
!189 = !DIDerivedType(tag: DW_TAG_member, name: "selector", scope: !187, file: !161, line: 20, baseType: !41, size: 64)
!190 = !DIDerivedType(tag: DW_TAG_member, name: "encoding", scope: !187, file: !161, line: 21, baseType: !41, size: 64, offset: 64)
!191 = !DIDerivedType(tag: DW_TAG_member, name: "imp", scope: !187, file: !161, line: 22, baseType: !192, size: 64, offset: 128)
!192 = !DIDerivedType(tag: DW_TAG_typedef, name: "IMP", file: !193, line: 61, baseType: !194)
!193 = !DIFile(filename: "/Applications/Xcode_16.app/Contents/Developer/Platforms/MacOSX.platform/Developer/SDKs/MacOSX15.0.sdk/usr/include/objc/objc.h", directory: "")
!194 = !DIDerivedType(tag: DW_TAG_pointer_type, baseType: !195, size: 64)
!195 = !DISubroutineType(types: !196)
!196 = !{null}
!197 = !DIDerivedType(tag: DW_TAG_member, name: "directAdapterNum", scope: !174, file: !161, line: 46, baseType: !29, size: 32, offset: 448)
!198 = !DIDerivedType(tag: DW_TAG_member, name: "classAdapters", scope: !174, file: !161, line: 48, baseType: !185, size: 64, offset: 512)
!199 = !DIDerivedType(tag: DW_TAG_member, name: "classAdapterNum", scope: !174, file: !161, line: 49, baseType: !29, size: 32, offset: 576)
!200 = !DIDerivedType(tag: DW_TAG_member, name: "virtualAdapters", scope: !174, file: !161, line: 51, baseType: !185, size: 64, offset: 640)
!201 = !DIDerivedType(tag: DW_TAG_member, name: "virtualAdapterNum", scope: !174, file: !161, line: 52, baseType: !29, size: 32, offset: 704)
!202 = !DIDerivedType(tag: DW_TAG_member, name: "reverseAdapters", scope: !174, file: !161, line: 54, baseType: !203, size: 64, offset: 768)
!203 = !DIDerivedType(tag: DW_TAG_pointer_type, baseType: !204, size: 64)
!204 = !DIDerivedType(tag: DW_TAG_const_type, baseType: !205)
!205 = distinct !DICompositeType(tag: DW_TAG_structure_type, name: "KotlinToObjCMethodAdapter", file: !161, line: 25, size: 256, flags: DIFlagTypePassByValue, elements: !206, identifier: "_ZTS25KotlinToObjCMethodAdapter")
!206 = !{!207, !208, !209, !210, !211, !212}
!207 = !DIDerivedType(tag: DW_TAG_member, name: "selector", scope: !205, file: !161, line: 26, baseType: !41, size: 64)
!208 = !DIDerivedType(tag: DW_TAG_member, name: "interfaceId", scope: !205, file: !161, line: 27, baseType: !66, size: 32, offset: 64)
!209 = !DIDerivedType(tag: DW_TAG_member, name: "itableSize", scope: !205, file: !161, line: 28, baseType: !29, size: 32, offset: 96)
!210 = !DIDerivedType(tag: DW_TAG_member, name: "itableIndex", scope: !205, file: !161, line: 29, baseType: !29, size: 32, offset: 128)
!211 = !DIDerivedType(tag: DW_TAG_member, name: "vtableIndex", scope: !205, file: !161, line: 30, baseType: !29, size: 32, offset: 160)
!212 = !DIDerivedType(tag: DW_TAG_member, name: "kotlinImpl", scope: !205, file: !161, line: 31, baseType: !72, size: 64, offset: 192)
!213 = !DIDerivedType(tag: DW_TAG_member, name: "reverseAdapterNum", scope: !174, file: !161, line: 55, baseType: !29, size: 32, offset: 832)
!214 = !DIDerivedType(tag: DW_TAG_member, name: "associatedObjects", scope: !17, file: !18, line: 137, baseType: !215, size: 64, offset: 832)
!215 = !DIDerivedType(tag: DW_TAG_pointer_type, baseType: !216, size: 64)
!216 = !DIDerivedType(tag: DW_TAG_const_type, baseType: !217)
!217 = distinct !DICompositeType(tag: DW_TAG_structure_type, name: "AssociatedObjectTableRecord", file: !18, line: 21, size: 128, flags: DIFlagTypePassByValue, elements: !218, identifier: "_ZTS27AssociatedObjectTableRecord")
!218 = !{!219, !220}
!219 = !DIDerivedType(tag: DW_TAG_member, name: "key", scope: !217, file: !18, line: 22, baseType: !15, size: 64)
!220 = !DIDerivedType(tag: DW_TAG_member, name: "getAssociatedObjectInstance", scope: !217, file: !18, line: 23, baseType: !221, size: 64, offset: 64)
!221 = !DIDerivedType(tag: DW_TAG_pointer_type, baseType: !222, size: 64)
!222 = !DISubroutineType(types: !223)
!223 = !{!75, !224}
!224 = !DIDerivedType(tag: DW_TAG_pointer_type, baseType: !75, size: 64)
!225 = !DIDerivedType(tag: DW_TAG_member, name: "processObjectInMark", scope: !17, file: !18, line: 141, baseType: !226, size: 64, offset: 896)
!226 = !DIDerivedType(tag: DW_TAG_pointer_type, baseType: !227, size: 64)
!227 = !DISubroutineType(types: !228)
!228 = !{null, !6, !75}
!229 = !DIDerivedType(tag: DW_TAG_member, name: "instanceAlignment_", scope: !17, file: !18, line: 144, baseType: !48, size: 32, offset: 960)
!230 = !DISubprogram(name: "vtable", linkageName: "_ZNK8TypeInfo6vtableEv", scope: !17, file: !18, line: 150, type: !231, scopeLine: 150, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!231 = !DISubroutineType(types: !232)
!232 = !{!69, !233}
!233 = !DIDerivedType(tag: DW_TAG_pointer_type, baseType: !16, size: 64, flags: DIFlagArtificial | DIFlagObjectPointer)
!234 = !DISubprogram(name: "vtable", linkageName: "_ZN8TypeInfo6vtableEv", scope: !17, file: !18, line: 152, type: !235, scopeLine: 152, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!235 = !DISubroutineType(types: !236)
!236 = !{!237, !238}
!237 = !DIDerivedType(tag: DW_TAG_pointer_type, baseType: !71, size: 64)
!238 = !DIDerivedType(tag: DW_TAG_pointer_type, baseType: !17, size: 64, flags: DIFlagArtificial | DIFlagObjectPointer)
!239 = !DISubprogram(name: "IsArray", linkageName: "_ZNK8TypeInfo7IsArrayEv", scope: !17, file: !18, line: 154, type: !240, scopeLine: 154, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!240 = !DISubroutineType(types: !241)
!241 = !{!98, !233}
!242 = !DISubprogram(name: "IsLayoutCompatible", linkageName: "_ZNK8TypeInfo18IsLayoutCompatibleEPKS_", scope: !17, file: !18, line: 156, type: !243, scopeLine: 156, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!243 = !DISubroutineType(types: !244)
!244 = !{!98, !233, !15}
!245 = !DIDerivedType(tag: DW_TAG_member, name: "objcClass", scope: !11, file: !12, line: 23, baseType: !168, size: 64, offset: 64)
!246 = !DIDerivedType(tag: DW_TAG_member, name: "bodyOffset", scope: !11, file: !12, line: 24, baseType: !27, size: 32, offset: 128)
!247 = !DIGlobalVariableExpression(var: !248, expr: !DIExpression())
!248 = distinct !DIGlobalVariable(name: "touchKotlinObjCClassInfo", scope: !2, file: !10, line: 22, type: !249, isLocal: false, isDefinition: true)
!249 = distinct !DICompositeType(tag: DW_TAG_structure_type, name: "KotlinObjCClassInfo", file: !12, line: 33, size: 832, flags: DIFlagTypePassByValue, elements: !250, identifier: "_ZTS19KotlinObjCClassInfo")
!250 = !{!251, !252, !253, !254, !255, !266, !267, !268, !269, !271, !272, !273, !274}
!251 = !DIDerivedType(tag: DW_TAG_member, name: "name", scope: !249, file: !12, line: 34, baseType: !41, size: 64)
!252 = !DIDerivedType(tag: DW_TAG_member, name: "exported", scope: !249, file: !12, line: 35, baseType: !29, size: 32, offset: 64)
!253 = !DIDerivedType(tag: DW_TAG_member, name: "superclassName", scope: !249, file: !12, line: 37, baseType: !41, size: 64, offset: 128)
!254 = !DIDerivedType(tag: DW_TAG_member, name: "protocolNames", scope: !249, file: !12, line: 38, baseType: !40, size: 64, offset: 192)
!255 = !DIDerivedType(tag: DW_TAG_member, name: "instanceMethods", scope: !249, file: !12, line: 40, baseType: !256, size: 64, offset: 256)
!256 = !DIDerivedType(tag: DW_TAG_pointer_type, baseType: !257, size: 64)
!257 = !DIDerivedType(tag: DW_TAG_const_type, baseType: !258)
!258 = distinct !DICompositeType(tag: DW_TAG_structure_type, name: "ObjCMethodDescription", file: !12, line: 27, size: 192, flags: DIFlagTypePassByValue, elements: !259, identifier: "_ZTS21ObjCMethodDescription")
!259 = !{!260, !264, !265}
!260 = !DIDerivedType(tag: DW_TAG_member, name: "imp", scope: !258, file: !12, line: 28, baseType: !261, size: 64)
!261 = !DIDerivedType(tag: DW_TAG_pointer_type, baseType: !262, size: 64)
!262 = !DISubroutineType(types: !263)
!263 = !{!6, !6, !6, null}
!264 = !DIDerivedType(tag: DW_TAG_member, name: "selector", scope: !258, file: !12, line: 29, baseType: !41, size: 64, offset: 64)
!265 = !DIDerivedType(tag: DW_TAG_member, name: "encoding", scope: !258, file: !12, line: 30, baseType: !41, size: 64, offset: 128)
!266 = !DIDerivedType(tag: DW_TAG_member, name: "instanceMethodsNum", scope: !249, file: !12, line: 41, baseType: !27, size: 32, offset: 320)
!267 = !DIDerivedType(tag: DW_TAG_member, name: "classMethods", scope: !249, file: !12, line: 43, baseType: !256, size: 64, offset: 384)
!268 = !DIDerivedType(tag: DW_TAG_member, name: "classMethodsNum", scope: !249, file: !12, line: 44, baseType: !27, size: 32, offset: 448)
!269 = !DIDerivedType(tag: DW_TAG_member, name: "bodyOffset", scope: !249, file: !12, line: 46, baseType: !270, size: 64, offset: 512)
!270 = !DIDerivedType(tag: DW_TAG_pointer_type, baseType: !27, size: 64)
!271 = !DIDerivedType(tag: DW_TAG_member, name: "typeInfo", scope: !249, file: !12, line: 48, baseType: !15, size: 64, offset: 576)
!272 = !DIDerivedType(tag: DW_TAG_member, name: "metaTypeInfo", scope: !249, file: !12, line: 49, baseType: !15, size: 64, offset: 640)
!273 = !DIDerivedType(tag: DW_TAG_member, name: "createdClass", scope: !249, file: !12, line: 51, baseType: !46, size: 64, offset: 704)
!274 = !DIDerivedType(tag: DW_TAG_member, name: "classDataImp", scope: !249, file: !12, line: 53, baseType: !275, size: 64, offset: 768)
!275 = !DIDerivedType(tag: DW_TAG_pointer_type, baseType: !276, size: 64)
!276 = !DISubroutineType(types: !277)
!277 = !{!278, !6, !6}
!278 = !DIDerivedType(tag: DW_TAG_pointer_type, baseType: !11, size: 64)
!279 = !DIGlobalVariableExpression(var: !280, expr: !DIExpression())
!280 = distinct !DIGlobalVariable(name: "touchObjCMethodDescription", scope: !2, file: !10, line: 23, type: !258, isLocal: false, isDefinition: true)
!281 = !DIGlobalVariableExpression(var: !282, expr: !DIExpression())
!282 = distinct !DIGlobalVariable(name: "touchObjCTypeAdapter", scope: !2, file: !10, line: 24, type: !174, isLocal: false, isDefinition: true)
!283 = !DIGlobalVariableExpression(var: !284, expr: !DIExpression())
!284 = distinct !DIGlobalVariable(name: "touchObjCToKotlinMethodAdapter", scope: !2, file: !10, line: 25, type: !187, isLocal: false, isDefinition: true)
!285 = !DIGlobalVariableExpression(var: !286, expr: !DIExpression())
!286 = distinct !DIGlobalVariable(name: "touchKotlinToObjCMethodAdapter", scope: !2, file: !10, line: 26, type: !205, isLocal: false, isDefinition: true)
!287 = !DIGlobalVariableExpression(var: !288, expr: !DIExpression())
!288 = distinct !DIGlobalVariable(name: "touchTypeInfoObjCExportAddition", scope: !2, file: !10, line: 27, type: !164, isLocal: false, isDefinition: true)
!289 = !DIGlobalVariableExpression(var: !290, expr: !DIExpression())
!290 = distinct !DIGlobalVariable(name: "touchBlock_literal_1", scope: !2, file: !10, line: 29, type: !291, isLocal: false, isDefinition: true)
!291 = distinct !DICompositeType(tag: DW_TAG_structure_type, name: "Block_literal_1", file: !161, line: 71, size: 256, flags: DIFlagTypePassByValue, elements: !292, identifier: "_ZTS15Block_literal_1")
!292 = !{!293, !294, !295, !296, !300}
!293 = !DIDerivedType(tag: DW_TAG_member, name: "isa", scope: !291, file: !161, line: 72, baseType: !6, size: 64)
!294 = !DIDerivedType(tag: DW_TAG_member, name: "flags", scope: !291, file: !161, line: 73, baseType: !29, size: 32, offset: 64)
!295 = !DIDerivedType(tag: DW_TAG_member, name: "reserved", scope: !291, file: !161, line: 74, baseType: !29, size: 32, offset: 96)
!296 = !DIDerivedType(tag: DW_TAG_member, name: "invoke", scope: !291, file: !161, line: 75, baseType: !297, size: 64, offset: 128)
!297 = !DIDerivedType(tag: DW_TAG_pointer_type, baseType: !298, size: 64)
!298 = !DISubroutineType(types: !299)
!299 = !{null, !6, null}
!300 = !DIDerivedType(tag: DW_TAG_member, name: "descriptor", scope: !291, file: !161, line: 76, baseType: !301, size: 64, offset: 192)
!301 = !DIDerivedType(tag: DW_TAG_pointer_type, baseType: !302, size: 64)
!302 = distinct !DICompositeType(tag: DW_TAG_structure_type, name: "Block_descriptor_1", file: !161, line: 83, size: 384, flags: DIFlagTypePassByValue, elements: !303, identifier: "_ZTS18Block_descriptor_1")
!303 = !{!304, !306, !307, !311, !315, !316}
!304 = !DIDerivedType(tag: DW_TAG_member, name: "reserved", scope: !302, file: !161, line: 84, baseType: !305, size: 64)
!305 = !DIBasicType(name: "long unsigned int", size: 64, encoding: DW_ATE_unsigned)
!306 = !DIDerivedType(tag: DW_TAG_member, name: "size", scope: !302, file: !161, line: 85, baseType: !305, size: 64, offset: 64)
!307 = !DIDerivedType(tag: DW_TAG_member, name: "copy_helper", scope: !302, file: !161, line: 88, baseType: !308, size: 64, offset: 128)
!308 = !DIDerivedType(tag: DW_TAG_pointer_type, baseType: !309, size: 64)
!309 = !DISubroutineType(types: !310)
!310 = !{null, !6, !6}
!311 = !DIDerivedType(tag: DW_TAG_member, name: "dispose_helper", scope: !302, file: !161, line: 89, baseType: !312, size: 64, offset: 192)
!312 = !DIDerivedType(tag: DW_TAG_pointer_type, baseType: !313, size: 64)
!313 = !DISubroutineType(types: !314)
!314 = !{null, !6}
!315 = !DIDerivedType(tag: DW_TAG_member, name: "signature", scope: !302, file: !161, line: 91, baseType: !41, size: 64, offset: 256)
!316 = !DIDerivedType(tag: DW_TAG_member, name: "layout", scope: !302, file: !161, line: 92, baseType: !72, size: 64, offset: 320)
!317 = !DIGlobalVariableExpression(var: !318, expr: !DIExpression())
!318 = distinct !DIGlobalVariable(name: "touchBlock_descriptor_1", scope: !2, file: !10, line: 30, type: !302, isLocal: false, isDefinition: true)
!319 = !{!320, !327, !332, !336, !341, !343, !348, !350, !355, !359, !361, !363, !367, !371, !375, !377, !381, !386, !390, !394, !396, !398, !400, !402, !404, !406, !410, !414, !419, !423, !424, !428, !429, !433, !434, !438, !441, !443, !445, !447, !449, !451, !453, !455, !457, !459, !461, !463, !465, !467, !469, !471, !475, !478, !481, !484, !486, !493, !499, !505, !510, !514, !518, !522, !527, !532, !536, !540, !544, !548, !552, !556, !560, !565, !567, !571, !575, !578, !582, !586, !589, !593, !595, !602, !606, !611, !615, !619, !623, !627, !629, !633, !639, !643, !647, !653, !658, !659, !663, !678, !682, !686, !691, !696, !702, !708, !712, !714, !718, !723, !778, !779, !780, !785, !787, !791, !795, !799, !801, !805, !809, !813, !821, !823, !827, !831, !835, !837, !841, !845, !849, !851, !853, !855, !859, !863, !868, !872, !878, !882, !886, !888, !890, !892, !896, !900, !904, !906, !908, !912, !916, !918, !922, !926, !929, !933, !935}
!320 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !323, file: !326, line: 49)
!321 = !DINamespace(name: "__1", scope: !322, exportSymbols: true)
!322 = !DINamespace(name: "std", scope: null)
!323 = !DIDerivedType(tag: DW_TAG_typedef, name: "ptrdiff_t", file: !324, line: 35, baseType: !325)
!324 = !DIFile(filename: ".konan/dependencies/apple-llvm-20200714-macos-aarch64-1/lib/clang/11.1.0/include/stddef.h", directory: "/Users/bytedance")
!325 = !DIBasicType(name: "long int", size: 64, encoding: DW_ATE_signed)
!326 = !DIFile(filename: ".konan/dependencies/apple-llvm-20200714-macos-aarch64-1/bin/../include/c++/v1/cstddef", directory: "/Users/bytedance")
!327 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !328, file: !326, line: 50)
!328 = !DIDerivedType(tag: DW_TAG_typedef, name: "size_t", file: !329, line: 50, baseType: !330)
!329 = !DIFile(filename: "/Applications/Xcode_16.app/Contents/Developer/Platforms/MacOSX.platform/Developer/SDKs/MacOSX15.0.sdk/usr/include/sys/_types/_size_t.h", directory: "")
!330 = !DIDerivedType(tag: DW_TAG_typedef, name: "__darwin_size_t", file: !331, line: 87, baseType: !305)
!331 = !DIFile(filename: "/Applications/Xcode_16.app/Contents/Developer/Platforms/MacOSX.platform/Developer/SDKs/MacOSX15.0.sdk/usr/include/arm/_types.h", directory: "")
!332 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !333, file: !326, line: 53)
!333 = !DIDerivedType(tag: DW_TAG_typedef, name: "max_align_t", file: !334, line: 16, baseType: !335)
!334 = !DIFile(filename: ".konan/dependencies/apple-llvm-20200714-macos-aarch64-1/lib/clang/11.1.0/include/__stddef_max_align_t.h", directory: "/Users/bytedance")
!335 = !DIBasicType(name: "long double", size: 64, encoding: DW_ATE_float)
!336 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !2, entity: !337, file: !340, line: 51)
!337 = !DIDerivedType(tag: DW_TAG_typedef, name: "nullptr_t", scope: !322, file: !338, line: 56, baseType: !339)
!338 = !DIFile(filename: ".konan/dependencies/apple-llvm-20200714-macos-aarch64-1/bin/../include/c++/v1/__nullptr", directory: "/Users/bytedance")
!339 = !DIBasicType(tag: DW_TAG_unspecified_type, name: "decltype(nullptr)")
!340 = !DIFile(filename: ".konan/dependencies/apple-llvm-20200714-macos-aarch64-1/bin/../include/c++/v1/stddef.h", directory: "/Users/bytedance")
!341 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !328, file: !342, line: 68)
!342 = !DIFile(filename: ".konan/dependencies/apple-llvm-20200714-macos-aarch64-1/bin/../include/c++/v1/cstring", directory: "/Users/bytedance")
!343 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !344, file: !342, line: 69)
!344 = !DISubprogram(name: "memcpy", scope: !345, file: !345, line: 72, type: !346, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!345 = !DIFile(filename: "/Applications/Xcode_16.app/Contents/Developer/Platforms/MacOSX.platform/Developer/SDKs/MacOSX15.0.sdk/usr/include/_string.h", directory: "")
!346 = !DISubroutineType(types: !347)
!347 = !{!6, !6, !72, !328}
!348 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !349, file: !342, line: 70)
!349 = !DISubprogram(name: "memmove", scope: !345, file: !345, line: 73, type: !346, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!350 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !351, file: !342, line: 71)
!351 = !DISubprogram(name: "strcpy", scope: !345, file: !345, line: 79, type: !352, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!352 = !DISubroutineType(types: !353)
!353 = !{!354, !354, !41}
!354 = !DIDerivedType(tag: DW_TAG_pointer_type, baseType: !43, size: 64)
!355 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !356, file: !342, line: 72)
!356 = !DISubprogram(name: "strncpy", scope: !345, file: !345, line: 85, type: !357, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!357 = !DISubroutineType(types: !358)
!358 = !{!354, !354, !41, !328}
!359 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !360, file: !342, line: 73)
!360 = !DISubprogram(name: "strcat", scope: !345, file: !345, line: 75, type: !352, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!361 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !362, file: !342, line: 74)
!362 = !DISubprogram(name: "strncat", scope: !345, file: !345, line: 83, type: !357, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!363 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !364, file: !342, line: 75)
!364 = !DISubprogram(name: "memcmp", scope: !345, file: !345, line: 71, type: !365, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!365 = !DISubroutineType(types: !366)
!366 = !{!29, !72, !72, !328}
!367 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !368, file: !342, line: 76)
!368 = !DISubprogram(name: "strcmp", scope: !345, file: !345, line: 77, type: !369, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!369 = !DISubroutineType(types: !370)
!370 = !{!29, !41, !41}
!371 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !372, file: !342, line: 77)
!372 = !DISubprogram(name: "strncmp", scope: !345, file: !345, line: 84, type: !373, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!373 = !DISubroutineType(types: !374)
!374 = !{!29, !41, !41, !328}
!375 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !376, file: !342, line: 78)
!376 = !DISubprogram(name: "strcoll", scope: !345, file: !345, line: 78, type: !369, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!377 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !378, file: !342, line: 79)
!378 = !DISubprogram(name: "strxfrm", scope: !345, file: !345, line: 91, type: !379, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!379 = !DISubroutineType(types: !380)
!380 = !{!328, !354, !41, !328}
!381 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !382, file: !342, line: 80)
!382 = !DISubprogram(name: "memchr", linkageName: "_Z6memchrUa9enable_ifIXLb1EEEPvim", scope: !383, file: !383, line: 98, type: !384, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!383 = !DIFile(filename: ".konan/dependencies/apple-llvm-20200714-macos-aarch64-1/bin/../include/c++/v1/string.h", directory: "/Users/bytedance")
!384 = !DISubroutineType(types: !385)
!385 = !{!6, !6, !29, !328}
!386 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !387, file: !342, line: 81)
!387 = !DISubprogram(name: "strchr", linkageName: "_Z6strchrUa9enable_ifIXLb1EEEPci", scope: !383, file: !383, line: 77, type: !388, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!388 = !DISubroutineType(types: !389)
!389 = !{!354, !354, !29}
!390 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !391, file: !342, line: 82)
!391 = !DISubprogram(name: "strcspn", scope: !345, file: !345, line: 80, type: !392, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!392 = !DISubroutineType(types: !393)
!393 = !{!328, !41, !41}
!394 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !395, file: !342, line: 83)
!395 = !DISubprogram(name: "strpbrk", linkageName: "_Z7strpbrkUa9enable_ifIXLb1EEEPcPKc", scope: !383, file: !383, line: 84, type: !352, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!396 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !397, file: !342, line: 84)
!397 = !DISubprogram(name: "strrchr", linkageName: "_Z7strrchrUa9enable_ifIXLb1EEEPci", scope: !383, file: !383, line: 91, type: !388, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!398 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !399, file: !342, line: 85)
!399 = !DISubprogram(name: "strspn", scope: !345, file: !345, line: 88, type: !392, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!400 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !401, file: !342, line: 86)
!401 = !DISubprogram(name: "strstr", linkageName: "_Z6strstrUa9enable_ifIXLb1EEEPcPKc", scope: !383, file: !383, line: 105, type: !352, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!402 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !403, file: !342, line: 88)
!403 = !DISubprogram(name: "strtok", scope: !345, file: !345, line: 90, type: !352, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!404 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !405, file: !342, line: 90)
!405 = !DISubprogram(name: "memset", scope: !345, file: !345, line: 74, type: !384, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!406 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !407, file: !342, line: 91)
!407 = !DISubprogram(name: "strerror", linkageName: "\01_strerror", scope: !345, file: !345, line: 81, type: !408, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!408 = !DISubroutineType(types: !409)
!409 = !{!354, !29}
!410 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !411, file: !342, line: 92)
!411 = !DISubprogram(name: "strlen", scope: !345, file: !345, line: 82, type: !412, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!412 = !DISubroutineType(types: !413)
!413 = !{!328, !41}
!414 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !415, file: !418, line: 152)
!415 = !DIDerivedType(tag: DW_TAG_typedef, name: "int8_t", file: !416, line: 30, baseType: !417)
!416 = !DIFile(filename: "/Applications/Xcode_16.app/Contents/Developer/Platforms/MacOSX.platform/Developer/SDKs/MacOSX15.0.sdk/usr/include/sys/_types/_int8_t.h", directory: "")
!417 = !DIBasicType(name: "signed char", size: 8, encoding: DW_ATE_signed_char)
!418 = !DIFile(filename: ".konan/dependencies/apple-llvm-20200714-macos-aarch64-1/bin/../include/c++/v1/cstdint", directory: "/Users/bytedance")
!419 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !420, file: !418, line: 153)
!420 = !DIDerivedType(tag: DW_TAG_typedef, name: "int16_t", file: !421, line: 30, baseType: !422)
!421 = !DIFile(filename: "/Applications/Xcode_16.app/Contents/Developer/Platforms/MacOSX.platform/Developer/SDKs/MacOSX15.0.sdk/usr/include/sys/_types/_int16_t.h", directory: "")
!422 = !DIBasicType(name: "short", size: 16, encoding: DW_ATE_signed)
!423 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !27, file: !418, line: 154)
!424 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !425, file: !418, line: 155)
!425 = !DIDerivedType(tag: DW_TAG_typedef, name: "int64_t", file: !426, line: 30, baseType: !427)
!426 = !DIFile(filename: "/Applications/Xcode_16.app/Contents/Developer/Platforms/MacOSX.platform/Developer/SDKs/MacOSX15.0.sdk/usr/include/sys/_types/_int64_t.h", directory: "")
!427 = !DIBasicType(name: "long long int", size: 64, encoding: DW_ATE_signed)
!428 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !36, file: !418, line: 157)
!429 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !430, file: !418, line: 158)
!430 = !DIDerivedType(tag: DW_TAG_typedef, name: "uint16_t", file: !431, line: 31, baseType: !432)
!431 = !DIFile(filename: "/Applications/Xcode_16.app/Contents/Developer/Platforms/MacOSX.platform/Developer/SDKs/MacOSX15.0.sdk/usr/include/_types/_uint16_t.h", directory: "")
!432 = !DIBasicType(name: "unsigned short", size: 16, encoding: DW_ATE_unsigned)
!433 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !48, file: !418, line: 159)
!434 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !435, file: !418, line: 160)
!435 = !DIDerivedType(tag: DW_TAG_typedef, name: "uint64_t", file: !436, line: 31, baseType: !437)
!436 = !DIFile(filename: "/Applications/Xcode_16.app/Contents/Developer/Platforms/MacOSX.platform/Developer/SDKs/MacOSX15.0.sdk/usr/include/_types/_uint64_t.h", directory: "")
!437 = !DIBasicType(name: "long long unsigned int", size: 64, encoding: DW_ATE_unsigned)
!438 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !439, file: !418, line: 162)
!439 = !DIDerivedType(tag: DW_TAG_typedef, name: "int_least8_t", file: !440, line: 29, baseType: !415)
!440 = !DIFile(filename: "/Applications/Xcode_16.app/Contents/Developer/Platforms/MacOSX.platform/Developer/SDKs/MacOSX15.0.sdk/usr/include/stdint.h", directory: "")
!441 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !442, file: !418, line: 163)
!442 = !DIDerivedType(tag: DW_TAG_typedef, name: "int_least16_t", file: !440, line: 30, baseType: !420)
!443 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !444, file: !418, line: 164)
!444 = !DIDerivedType(tag: DW_TAG_typedef, name: "int_least32_t", file: !440, line: 31, baseType: !27)
!445 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !446, file: !418, line: 165)
!446 = !DIDerivedType(tag: DW_TAG_typedef, name: "int_least64_t", file: !440, line: 32, baseType: !425)
!447 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !448, file: !418, line: 167)
!448 = !DIDerivedType(tag: DW_TAG_typedef, name: "uint_least8_t", file: !440, line: 33, baseType: !36)
!449 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !450, file: !418, line: 168)
!450 = !DIDerivedType(tag: DW_TAG_typedef, name: "uint_least16_t", file: !440, line: 34, baseType: !430)
!451 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !452, file: !418, line: 169)
!452 = !DIDerivedType(tag: DW_TAG_typedef, name: "uint_least32_t", file: !440, line: 35, baseType: !48)
!453 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !454, file: !418, line: 170)
!454 = !DIDerivedType(tag: DW_TAG_typedef, name: "uint_least64_t", file: !440, line: 36, baseType: !435)
!455 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !456, file: !418, line: 172)
!456 = !DIDerivedType(tag: DW_TAG_typedef, name: "int_fast8_t", file: !440, line: 40, baseType: !415)
!457 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !458, file: !418, line: 173)
!458 = !DIDerivedType(tag: DW_TAG_typedef, name: "int_fast16_t", file: !440, line: 41, baseType: !420)
!459 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !460, file: !418, line: 174)
!460 = !DIDerivedType(tag: DW_TAG_typedef, name: "int_fast32_t", file: !440, line: 42, baseType: !27)
!461 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !462, file: !418, line: 175)
!462 = !DIDerivedType(tag: DW_TAG_typedef, name: "int_fast64_t", file: !440, line: 43, baseType: !425)
!463 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !464, file: !418, line: 177)
!464 = !DIDerivedType(tag: DW_TAG_typedef, name: "uint_fast8_t", file: !440, line: 44, baseType: !36)
!465 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !466, file: !418, line: 178)
!466 = !DIDerivedType(tag: DW_TAG_typedef, name: "uint_fast16_t", file: !440, line: 45, baseType: !430)
!467 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !468, file: !418, line: 179)
!468 = !DIDerivedType(tag: DW_TAG_typedef, name: "uint_fast32_t", file: !440, line: 46, baseType: !48)
!469 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !470, file: !418, line: 180)
!470 = !DIDerivedType(tag: DW_TAG_typedef, name: "uint_fast64_t", file: !440, line: 47, baseType: !435)
!471 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !472, file: !418, line: 182)
!472 = !DIDerivedType(tag: DW_TAG_typedef, name: "intptr_t", file: !473, line: 32, baseType: !474)
!473 = !DIFile(filename: "/Applications/Xcode_16.app/Contents/Developer/Platforms/MacOSX.platform/Developer/SDKs/MacOSX15.0.sdk/usr/include/sys/_types/_intptr_t.h", directory: "")
!474 = !DIDerivedType(tag: DW_TAG_typedef, name: "__darwin_intptr_t", file: !331, line: 40, baseType: !325)
!475 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !476, file: !418, line: 183)
!476 = !DIDerivedType(tag: DW_TAG_typedef, name: "uintptr_t", file: !477, line: 34, baseType: !305)
!477 = !DIFile(filename: "/Applications/Xcode_16.app/Contents/Developer/Platforms/MacOSX.platform/Developer/SDKs/MacOSX15.0.sdk/usr/include/sys/_types/_uintptr_t.h", directory: "")
!478 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !479, file: !418, line: 185)
!479 = !DIDerivedType(tag: DW_TAG_typedef, name: "intmax_t", file: !480, line: 32, baseType: !325)
!480 = !DIFile(filename: "/Applications/Xcode_16.app/Contents/Developer/Platforms/MacOSX.platform/Developer/SDKs/MacOSX15.0.sdk/usr/include/_types/_intmax_t.h", directory: "")
!481 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !482, file: !418, line: 186)
!482 = !DIDerivedType(tag: DW_TAG_typedef, name: "uintmax_t", file: !483, line: 32, baseType: !305)
!483 = !DIFile(filename: "/Applications/Xcode_16.app/Contents/Developer/Platforms/MacOSX.platform/Developer/SDKs/MacOSX15.0.sdk/usr/include/_types/_uintmax_t.h", directory: "")
!484 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !328, file: !485, line: 99)
!485 = !DIFile(filename: ".konan/dependencies/apple-llvm-20200714-macos-aarch64-1/bin/../include/c++/v1/cstdlib", directory: "/Users/bytedance")
!486 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !487, file: !485, line: 100)
!487 = !DIDerivedType(tag: DW_TAG_typedef, name: "div_t", file: !488, line: 86, baseType: !489)
!488 = !DIFile(filename: "/Applications/Xcode_16.app/Contents/Developer/Platforms/MacOSX.platform/Developer/SDKs/MacOSX15.0.sdk/usr/include/_stdlib.h", directory: "")
!489 = distinct !DICompositeType(tag: DW_TAG_structure_type, file: !488, line: 83, size: 64, flags: DIFlagTypePassByValue, elements: !490, identifier: "_ZTS5div_t")
!490 = !{!491, !492}
!491 = !DIDerivedType(tag: DW_TAG_member, name: "quot", scope: !489, file: !488, line: 84, baseType: !29, size: 32)
!492 = !DIDerivedType(tag: DW_TAG_member, name: "rem", scope: !489, file: !488, line: 85, baseType: !29, size: 32, offset: 32)
!493 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !494, file: !485, line: 101)
!494 = !DIDerivedType(tag: DW_TAG_typedef, name: "ldiv_t", file: !488, line: 91, baseType: !495)
!495 = distinct !DICompositeType(tag: DW_TAG_structure_type, file: !488, line: 88, size: 128, flags: DIFlagTypePassByValue, elements: !496, identifier: "_ZTS6ldiv_t")
!496 = !{!497, !498}
!497 = !DIDerivedType(tag: DW_TAG_member, name: "quot", scope: !495, file: !488, line: 89, baseType: !325, size: 64)
!498 = !DIDerivedType(tag: DW_TAG_member, name: "rem", scope: !495, file: !488, line: 90, baseType: !325, size: 64, offset: 64)
!499 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !500, file: !485, line: 103)
!500 = !DIDerivedType(tag: DW_TAG_typedef, name: "lldiv_t", file: !488, line: 97, baseType: !501)
!501 = distinct !DICompositeType(tag: DW_TAG_structure_type, file: !488, line: 94, size: 128, flags: DIFlagTypePassByValue, elements: !502, identifier: "_ZTS7lldiv_t")
!502 = !{!503, !504}
!503 = !DIDerivedType(tag: DW_TAG_member, name: "quot", scope: !501, file: !488, line: 95, baseType: !427, size: 64)
!504 = !DIDerivedType(tag: DW_TAG_member, name: "rem", scope: !501, file: !488, line: 96, baseType: !427, size: 64, offset: 64)
!505 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !506, file: !485, line: 105)
!506 = !DISubprogram(name: "atof", scope: !488, file: !488, line: 130, type: !507, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!507 = !DISubroutineType(types: !508)
!508 = !{!509, !41}
!509 = !DIBasicType(name: "double", size: 64, encoding: DW_ATE_float)
!510 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !511, file: !485, line: 106)
!511 = !DISubprogram(name: "atoi", scope: !488, file: !488, line: 131, type: !512, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!512 = !DISubroutineType(types: !513)
!513 = !{!29, !41}
!514 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !515, file: !485, line: 107)
!515 = !DISubprogram(name: "atol", scope: !488, file: !488, line: 132, type: !516, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!516 = !DISubroutineType(types: !517)
!517 = !{!325, !41}
!518 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !519, file: !485, line: 109)
!519 = !DISubprogram(name: "atoll", scope: !488, file: !488, line: 135, type: !520, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!520 = !DISubroutineType(types: !521)
!521 = !{!427, !41}
!522 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !523, file: !485, line: 111)
!523 = !DISubprogram(name: "strtod", linkageName: "\01_strtod", scope: !488, file: !488, line: 165, type: !524, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!524 = !DISubroutineType(types: !525)
!525 = !{!509, !41, !526}
!526 = !DIDerivedType(tag: DW_TAG_pointer_type, baseType: !354, size: 64)
!527 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !528, file: !485, line: 112)
!528 = !DISubprogram(name: "strtof", linkageName: "\01_strtof", scope: !488, file: !488, line: 166, type: !529, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!529 = !DISubroutineType(types: !530)
!530 = !{!531, !41, !526}
!531 = !DIBasicType(name: "float", size: 32, encoding: DW_ATE_float)
!532 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !533, file: !485, line: 113)
!533 = !DISubprogram(name: "strtold", scope: !488, file: !488, line: 169, type: !534, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!534 = !DISubroutineType(types: !535)
!535 = !{!335, !41, !526}
!536 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !537, file: !485, line: 114)
!537 = !DISubprogram(name: "strtol", scope: !488, file: !488, line: 167, type: !538, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!538 = !DISubroutineType(types: !539)
!539 = !{!325, !41, !526, !29}
!540 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !541, file: !485, line: 116)
!541 = !DISubprogram(name: "strtoll", scope: !488, file: !488, line: 172, type: !542, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!542 = !DISubroutineType(types: !543)
!543 = !{!427, !41, !526, !29}
!544 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !545, file: !485, line: 118)
!545 = !DISubprogram(name: "strtoul", scope: !488, file: !488, line: 175, type: !546, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!546 = !DISubroutineType(types: !547)
!547 = !{!305, !41, !526, !29}
!548 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !549, file: !485, line: 120)
!549 = !DISubprogram(name: "strtoull", scope: !488, file: !488, line: 178, type: !550, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!550 = !DISubroutineType(types: !551)
!551 = !{!437, !41, !526, !29}
!552 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !553, file: !485, line: 122)
!553 = !DISubprogram(name: "rand", scope: !488, file: !488, line: 162, type: !554, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!554 = !DISubroutineType(types: !555)
!555 = !{!29}
!556 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !557, file: !485, line: 123)
!557 = !DISubprogram(name: "srand", scope: !488, file: !488, line: 164, type: !558, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!558 = !DISubroutineType(types: !559)
!559 = !{null, !50}
!560 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !561, file: !485, line: 124)
!561 = !DISubprogram(name: "calloc", scope: !562, file: !562, line: 55, type: !563, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!562 = !DIFile(filename: "/Applications/Xcode_16.app/Contents/Developer/Platforms/MacOSX.platform/Developer/SDKs/MacOSX15.0.sdk/usr/include/malloc/_malloc.h", directory: "")
!563 = !DISubroutineType(types: !564)
!564 = !{!6, !328, !328}
!565 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !566, file: !485, line: 125)
!566 = !DISubprogram(name: "free", scope: !562, file: !562, line: 56, type: !313, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!567 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !568, file: !485, line: 126)
!568 = !DISubprogram(name: "malloc", scope: !562, file: !562, line: 54, type: !569, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!569 = !DISubroutineType(types: !570)
!570 = !{!6, !328}
!571 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !572, file: !485, line: 127)
!572 = !DISubprogram(name: "realloc", scope: !562, file: !562, line: 57, type: !573, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!573 = !DISubroutineType(types: !574)
!574 = !{!6, !6, !328}
!575 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !576, file: !485, line: 128)
!576 = !DISubprogram(name: "abort", scope: !577, file: !577, line: 30, type: !195, flags: DIFlagPrototyped | DIFlagNoReturn, spFlags: DISPFlagOptimized)
!577 = !DIFile(filename: "/Applications/Xcode_16.app/Contents/Developer/Platforms/MacOSX.platform/Developer/SDKs/MacOSX15.0.sdk/usr/include/_abort.h", directory: "")
!578 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !579, file: !485, line: 129)
!579 = !DISubprogram(name: "atexit", scope: !488, file: !488, line: 128, type: !580, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!580 = !DISubroutineType(types: !581)
!581 = !{!29, !194}
!582 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !583, file: !485, line: 130)
!583 = !DISubprogram(name: "exit", scope: !488, file: !488, line: 160, type: !584, flags: DIFlagPrototyped | DIFlagNoReturn, spFlags: DISPFlagOptimized)
!584 = !DISubroutineType(types: !585)
!585 = !{null, !29}
!586 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !587, file: !485, line: 131)
!587 = !DISubprogram(name: "_Exit", scope: !588, file: !588, line: 646, type: !584, flags: DIFlagPrototyped | DIFlagNoReturn, spFlags: DISPFlagOptimized)
!588 = !DIFile(filename: "/Applications/Xcode_16.app/Contents/Developer/Platforms/MacOSX.platform/Developer/SDKs/MacOSX15.0.sdk/usr/include/unistd.h", directory: "")
!589 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !590, file: !485, line: 133)
!590 = !DISubprogram(name: "getenv", scope: !488, file: !488, line: 143, type: !591, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!591 = !DISubroutineType(types: !592)
!592 = !{!354, !41}
!593 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !594, file: !485, line: 134)
!594 = !DISubprogram(name: "system", linkageName: "\01_system", scope: !488, file: !488, line: 184, type: !512, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!595 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !596, file: !485, line: 136)
!596 = !DISubprogram(name: "bsearch", scope: !488, file: !488, line: 137, type: !597, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!597 = !DISubroutineType(types: !598)
!598 = !{!6, !72, !72, !328, !328, !599}
!599 = !DIDerivedType(tag: DW_TAG_pointer_type, baseType: !600, size: 64)
!600 = !DISubroutineType(types: !601)
!601 = !{!29, !72, !72}
!602 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !603, file: !485, line: 137)
!603 = !DISubprogram(name: "qsort", scope: !488, file: !488, line: 156, type: !604, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!604 = !DISubroutineType(types: !605)
!605 = !{null, !6, !328, !328, !599}
!606 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !607, file: !485, line: 138)
!607 = !DISubprogram(name: "abs", linkageName: "_Z3abse", scope: !608, file: !608, line: 127, type: !609, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!608 = !DIFile(filename: ".konan/dependencies/apple-llvm-20200714-macos-aarch64-1/bin/../include/c++/v1/stdlib.h", directory: "/Users/bytedance")
!609 = !DISubroutineType(types: !610)
!610 = !{!335, !335}
!611 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !612, file: !485, line: 139)
!612 = !DISubprogram(name: "labs", scope: !488, file: !488, line: 144, type: !613, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!613 = !DISubroutineType(types: !614)
!614 = !{!325, !325}
!615 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !616, file: !485, line: 141)
!616 = !DISubprogram(name: "llabs", scope: !488, file: !488, line: 148, type: !617, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!617 = !DISubroutineType(types: !618)
!618 = !{!427, !427}
!619 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !620, file: !485, line: 143)
!620 = !DISubprogram(name: "div", linkageName: "_Z3divxx", scope: !608, file: !608, line: 146, type: !621, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!621 = !DISubroutineType(types: !622)
!622 = !{!500, !427, !427}
!623 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !624, file: !485, line: 144)
!624 = !DISubprogram(name: "ldiv", scope: !488, file: !488, line: 145, type: !625, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!625 = !DISubroutineType(types: !626)
!626 = !{!494, !325, !325}
!627 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !628, file: !485, line: 146)
!628 = !DISubprogram(name: "lldiv", scope: !488, file: !488, line: 149, type: !621, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!629 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !630, file: !485, line: 148)
!630 = !DISubprogram(name: "mblen", scope: !488, file: !488, line: 152, type: !631, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!631 = !DISubroutineType(types: !632)
!632 = !{!29, !41, !328}
!633 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !634, file: !485, line: 149)
!634 = !DISubprogram(name: "mbtowc", scope: !488, file: !488, line: 154, type: !635, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!635 = !DISubroutineType(types: !636)
!636 = !{!29, !637, !41, !328}
!637 = !DIDerivedType(tag: DW_TAG_pointer_type, baseType: !638, size: 64)
!638 = !DIBasicType(name: "wchar_t", size: 32, encoding: DW_ATE_signed)
!639 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !640, file: !485, line: 150)
!640 = !DISubprogram(name: "wctomb", scope: !488, file: !488, line: 188, type: !641, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!641 = !DISubroutineType(types: !642)
!642 = !{!29, !354, !638}
!643 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !644, file: !485, line: 151)
!644 = !DISubprogram(name: "mbstowcs", scope: !488, file: !488, line: 153, type: !645, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!645 = !DISubroutineType(types: !646)
!646 = !{!328, !637, !41, !328}
!647 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !648, file: !485, line: 152)
!648 = !DISubprogram(name: "wcstombs", scope: !488, file: !488, line: 187, type: !649, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!649 = !DISubroutineType(types: !650)
!650 = !{!328, !354, !651, !328}
!651 = !DIDerivedType(tag: DW_TAG_pointer_type, baseType: !652, size: 64)
!652 = !DIDerivedType(tag: DW_TAG_const_type, baseType: !638)
!653 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !654, file: !657, line: 57)
!654 = !DIDerivedType(tag: DW_TAG_typedef, name: "clock_t", file: !655, line: 31, baseType: !656)
!655 = !DIFile(filename: "/Applications/Xcode_16.app/Contents/Developer/Platforms/MacOSX.platform/Developer/SDKs/MacOSX15.0.sdk/usr/include/sys/_types/_clock_t.h", directory: "")
!656 = !DIDerivedType(tag: DW_TAG_typedef, name: "__darwin_clock_t", file: !331, line: 116, baseType: !305)
!657 = !DIFile(filename: ".konan/dependencies/apple-llvm-20200714-macos-aarch64-1/bin/../include/c++/v1/ctime", directory: "/Users/bytedance")
!658 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !328, file: !657, line: 58)
!659 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !660, file: !657, line: 59)
!660 = !DIDerivedType(tag: DW_TAG_typedef, name: "time_t", file: !661, line: 31, baseType: !662)
!661 = !DIFile(filename: "/Applications/Xcode_16.app/Contents/Developer/Platforms/MacOSX.platform/Developer/SDKs/MacOSX15.0.sdk/usr/include/sys/_types/_time_t.h", directory: "")
!662 = !DIDerivedType(tag: DW_TAG_typedef, name: "__darwin_time_t", file: !331, line: 119, baseType: !325)
!663 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !664, file: !657, line: 60)
!664 = distinct !DICompositeType(tag: DW_TAG_structure_type, name: "tm", file: !665, line: 75, size: 448, flags: DIFlagTypePassByValue, elements: !666, identifier: "_ZTS2tm")
!665 = !DIFile(filename: "/Applications/Xcode_16.app/Contents/Developer/Platforms/MacOSX.platform/Developer/SDKs/MacOSX15.0.sdk/usr/include/_time.h", directory: "")
!666 = !{!667, !668, !669, !670, !671, !672, !673, !674, !675, !676, !677}
!667 = !DIDerivedType(tag: DW_TAG_member, name: "tm_sec", scope: !664, file: !665, line: 76, baseType: !29, size: 32)
!668 = !DIDerivedType(tag: DW_TAG_member, name: "tm_min", scope: !664, file: !665, line: 77, baseType: !29, size: 32, offset: 32)
!669 = !DIDerivedType(tag: DW_TAG_member, name: "tm_hour", scope: !664, file: !665, line: 78, baseType: !29, size: 32, offset: 64)
!670 = !DIDerivedType(tag: DW_TAG_member, name: "tm_mday", scope: !664, file: !665, line: 79, baseType: !29, size: 32, offset: 96)
!671 = !DIDerivedType(tag: DW_TAG_member, name: "tm_mon", scope: !664, file: !665, line: 80, baseType: !29, size: 32, offset: 128)
!672 = !DIDerivedType(tag: DW_TAG_member, name: "tm_year", scope: !664, file: !665, line: 81, baseType: !29, size: 32, offset: 160)
!673 = !DIDerivedType(tag: DW_TAG_member, name: "tm_wday", scope: !664, file: !665, line: 82, baseType: !29, size: 32, offset: 192)
!674 = !DIDerivedType(tag: DW_TAG_member, name: "tm_yday", scope: !664, file: !665, line: 83, baseType: !29, size: 32, offset: 224)
!675 = !DIDerivedType(tag: DW_TAG_member, name: "tm_isdst", scope: !664, file: !665, line: 84, baseType: !29, size: 32, offset: 256)
!676 = !DIDerivedType(tag: DW_TAG_member, name: "tm_gmtoff", scope: !664, file: !665, line: 85, baseType: !325, size: 64, offset: 320)
!677 = !DIDerivedType(tag: DW_TAG_member, name: "tm_zone", scope: !664, file: !665, line: 86, baseType: !354, size: 64, offset: 384)
!678 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !679, file: !657, line: 64)
!679 = !DISubprogram(name: "clock", linkageName: "\01_clock", scope: !665, file: !665, line: 109, type: !680, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!680 = !DISubroutineType(types: !681)
!681 = !{!654}
!682 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !683, file: !657, line: 65)
!683 = !DISubprogram(name: "difftime", scope: !665, file: !665, line: 111, type: !684, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!684 = !DISubroutineType(types: !685)
!685 = !{!509, !660, !660}
!686 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !687, file: !657, line: 66)
!687 = !DISubprogram(name: "mktime", linkageName: "\01_mktime", scope: !665, file: !665, line: 115, type: !688, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!688 = !DISubroutineType(types: !689)
!689 = !{!660, !690}
!690 = !DIDerivedType(tag: DW_TAG_pointer_type, baseType: !664, size: 64)
!691 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !692, file: !657, line: 67)
!692 = !DISubprogram(name: "time", scope: !665, file: !665, line: 118, type: !693, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!693 = !DISubroutineType(types: !694)
!694 = !{!660, !695}
!695 = !DIDerivedType(tag: DW_TAG_pointer_type, baseType: !660, size: 64)
!696 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !697, file: !657, line: 69)
!697 = !DISubprogram(name: "asctime", scope: !665, file: !665, line: 108, type: !698, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!698 = !DISubroutineType(types: !699)
!699 = !{!354, !700}
!700 = !DIDerivedType(tag: DW_TAG_pointer_type, baseType: !701, size: 64)
!701 = !DIDerivedType(tag: DW_TAG_const_type, baseType: !664)
!702 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !703, file: !657, line: 70)
!703 = !DISubprogram(name: "ctime", scope: !665, file: !665, line: 110, type: !704, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!704 = !DISubroutineType(types: !705)
!705 = !{!354, !706}
!706 = !DIDerivedType(tag: DW_TAG_pointer_type, baseType: !707, size: 64)
!707 = !DIDerivedType(tag: DW_TAG_const_type, baseType: !660)
!708 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !709, file: !657, line: 71)
!709 = !DISubprogram(name: "gmtime", scope: !665, file: !665, line: 113, type: !710, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!710 = !DISubroutineType(types: !711)
!711 = !{!690, !706}
!712 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !713, file: !657, line: 72)
!713 = !DISubprogram(name: "localtime", scope: !665, file: !665, line: 114, type: !710, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!714 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !715, file: !657, line: 74)
!715 = !DISubprogram(name: "strftime", linkageName: "\01_strftime", scope: !665, file: !665, line: 116, type: !716, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!716 = !DISubroutineType(types: !717)
!717 = !{!328, !354, !328, !41, !700}
!718 = !DIImportedEntity(tag: DW_TAG_imported_module, scope: !719, entity: !720, file: !722, line: 2922)
!719 = !DINamespace(name: "chrono", scope: !321)
!720 = !DINamespace(name: "chrono_literals", scope: !721, exportSymbols: true)
!721 = !DINamespace(name: "literals", scope: !321, exportSymbols: true)
!722 = !DIFile(filename: ".konan/dependencies/apple-llvm-20200714-macos-aarch64-1/bin/../include/c++/v1/chrono", directory: "/Users/bytedance")
!723 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !724, file: !777, line: 107)
!724 = !DIDerivedType(tag: DW_TAG_typedef, name: "FILE", file: !725, line: 159, baseType: !726)
!725 = !DIFile(filename: "/Applications/Xcode_16.app/Contents/Developer/Platforms/MacOSX.platform/Developer/SDKs/MacOSX15.0.sdk/usr/include/_stdio.h", directory: "")
!726 = distinct !DICompositeType(tag: DW_TAG_structure_type, name: "__sFILE", file: !725, line: 128, size: 1216, flags: DIFlagTypePassByValue, elements: !727, identifier: "_ZTS7__sFILE")
!727 = !{!728, !730, !731, !732, !733, !734, !739, !740, !741, !745, !749, !757, !761, !762, !765, !766, !770, !774, !775, !776}
!728 = !DIDerivedType(tag: DW_TAG_member, name: "_p", scope: !726, file: !725, line: 129, baseType: !729, size: 64)
!729 = !DIDerivedType(tag: DW_TAG_pointer_type, baseType: !38, size: 64)
!730 = !DIDerivedType(tag: DW_TAG_member, name: "_r", scope: !726, file: !725, line: 130, baseType: !29, size: 32, offset: 64)
!731 = !DIDerivedType(tag: DW_TAG_member, name: "_w", scope: !726, file: !725, line: 131, baseType: !29, size: 32, offset: 96)
!732 = !DIDerivedType(tag: DW_TAG_member, name: "_flags", scope: !726, file: !725, line: 132, baseType: !422, size: 16, offset: 128)
!733 = !DIDerivedType(tag: DW_TAG_member, name: "_file", scope: !726, file: !725, line: 133, baseType: !422, size: 16, offset: 144)
!734 = !DIDerivedType(tag: DW_TAG_member, name: "_bf", scope: !726, file: !725, line: 134, baseType: !735, size: 128, offset: 192)
!735 = distinct !DICompositeType(tag: DW_TAG_structure_type, name: "__sbuf", file: !725, line: 94, size: 128, flags: DIFlagTypePassByValue, elements: !736, identifier: "_ZTS6__sbuf")
!736 = !{!737, !738}
!737 = !DIDerivedType(tag: DW_TAG_member, name: "_base", scope: !735, file: !725, line: 95, baseType: !729, size: 64)
!738 = !DIDerivedType(tag: DW_TAG_member, name: "_size", scope: !735, file: !725, line: 96, baseType: !29, size: 32, offset: 64)
!739 = !DIDerivedType(tag: DW_TAG_member, name: "_lbfsize", scope: !726, file: !725, line: 135, baseType: !29, size: 32, offset: 320)
!740 = !DIDerivedType(tag: DW_TAG_member, name: "_cookie", scope: !726, file: !725, line: 138, baseType: !6, size: 64, offset: 384)
!741 = !DIDerivedType(tag: DW_TAG_member, name: "_close", scope: !726, file: !725, line: 139, baseType: !742, size: 64, offset: 448)
!742 = !DIDerivedType(tag: DW_TAG_pointer_type, baseType: !743, size: 64)
!743 = !DISubroutineType(types: !744)
!744 = !{!29, !6}
!745 = !DIDerivedType(tag: DW_TAG_member, name: "_read", scope: !726, file: !725, line: 140, baseType: !746, size: 64, offset: 512)
!746 = !DIDerivedType(tag: DW_TAG_pointer_type, baseType: !747, size: 64)
!747 = !DISubroutineType(types: !748)
!748 = !{!29, !6, !354, !29}
!749 = !DIDerivedType(tag: DW_TAG_member, name: "_seek", scope: !726, file: !725, line: 141, baseType: !750, size: 64, offset: 576)
!750 = !DIDerivedType(tag: DW_TAG_pointer_type, baseType: !751, size: 64)
!751 = !DISubroutineType(types: !752)
!752 = !{!753, !6, !753, !29}
!753 = !DIDerivedType(tag: DW_TAG_typedef, name: "fpos_t", file: !725, line: 83, baseType: !754)
!754 = !DIDerivedType(tag: DW_TAG_typedef, name: "__darwin_off_t", file: !755, line: 83, baseType: !756)
!755 = !DIFile(filename: "/Applications/Xcode_16.app/Contents/Developer/Platforms/MacOSX.platform/Developer/SDKs/MacOSX15.0.sdk/usr/include/sys/_types.h", directory: "")
!756 = !DIDerivedType(tag: DW_TAG_typedef, name: "__int64_t", file: !331, line: 37, baseType: !427)
!757 = !DIDerivedType(tag: DW_TAG_member, name: "_write", scope: !726, file: !725, line: 142, baseType: !758, size: 64, offset: 640)
!758 = !DIDerivedType(tag: DW_TAG_pointer_type, baseType: !759, size: 64)
!759 = !DISubroutineType(types: !760)
!760 = !{!29, !6, !41, !29}
!761 = !DIDerivedType(tag: DW_TAG_member, name: "_ub", scope: !726, file: !725, line: 145, baseType: !735, size: 128, offset: 704)
!762 = !DIDerivedType(tag: DW_TAG_member, name: "_extra", scope: !726, file: !725, line: 146, baseType: !763, size: 64, offset: 832)
!763 = !DIDerivedType(tag: DW_TAG_pointer_type, baseType: !764, size: 64)
!764 = distinct !DICompositeType(tag: DW_TAG_structure_type, name: "__sFILEX", file: !725, line: 100, flags: DIFlagFwdDecl | DIFlagNonTrivial, identifier: "_ZTS8__sFILEX")
!765 = !DIDerivedType(tag: DW_TAG_member, name: "_ur", scope: !726, file: !725, line: 147, baseType: !29, size: 32, offset: 896)
!766 = !DIDerivedType(tag: DW_TAG_member, name: "_ubuf", scope: !726, file: !725, line: 150, baseType: !767, size: 24, offset: 928)
!767 = !DICompositeType(tag: DW_TAG_array_type, baseType: !38, size: 24, elements: !768)
!768 = !{!769}
!769 = !DISubrange(count: 3)
!770 = !DIDerivedType(tag: DW_TAG_member, name: "_nbuf", scope: !726, file: !725, line: 151, baseType: !771, size: 8, offset: 952)
!771 = !DICompositeType(tag: DW_TAG_array_type, baseType: !38, size: 8, elements: !772)
!772 = !{!773}
!773 = !DISubrange(count: 1)
!774 = !DIDerivedType(tag: DW_TAG_member, name: "_lb", scope: !726, file: !725, line: 154, baseType: !735, size: 128, offset: 960)
!775 = !DIDerivedType(tag: DW_TAG_member, name: "_blksize", scope: !726, file: !725, line: 157, baseType: !29, size: 32, offset: 1088)
!776 = !DIDerivedType(tag: DW_TAG_member, name: "_offset", scope: !726, file: !725, line: 158, baseType: !753, size: 64, offset: 1152)
!777 = !DIFile(filename: ".konan/dependencies/apple-llvm-20200714-macos-aarch64-1/bin/../include/c++/v1/cstdio", directory: "/Users/bytedance")
!778 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !753, file: !777, line: 108)
!779 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !328, file: !777, line: 109)
!780 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !781, file: !777, line: 111)
!781 = !DISubprogram(name: "fclose", scope: !725, file: !725, line: 230, type: !782, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!782 = !DISubroutineType(types: !783)
!783 = !{!29, !784}
!784 = !DIDerivedType(tag: DW_TAG_pointer_type, baseType: !724, size: 64)
!785 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !786, file: !777, line: 112)
!786 = !DISubprogram(name: "fflush", scope: !725, file: !725, line: 233, type: !782, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!787 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !788, file: !777, line: 113)
!788 = !DISubprogram(name: "setbuf", scope: !725, file: !725, line: 269, type: !789, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!789 = !DISubroutineType(types: !790)
!790 = !{null, !784, !354}
!791 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !792, file: !777, line: 114)
!792 = !DISubprogram(name: "setvbuf", scope: !725, file: !725, line: 270, type: !793, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!793 = !DISubroutineType(types: !794)
!794 = !{!29, !784, !354, !29, !328}
!795 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !796, file: !777, line: 115)
!796 = !DISubprogram(name: "fprintf", scope: !725, file: !725, line: 242, type: !797, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!797 = !DISubroutineType(types: !798)
!798 = !{!29, !784, !41, null}
!799 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !800, file: !777, line: 116)
!800 = !DISubprogram(name: "fscanf", scope: !725, file: !725, line: 248, type: !797, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!801 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !802, file: !777, line: 117)
!802 = !DISubprogram(name: "snprintf", scope: !725, file: !725, line: 431, type: !803, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!803 = !DISubroutineType(types: !804)
!804 = !{!29, !354, !328, !41, null}
!805 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !806, file: !777, line: 118)
!806 = !DISubprogram(name: "sprintf", scope: !725, file: !725, line: 276, type: !807, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!807 = !DISubroutineType(types: !808)
!808 = !{!29, !354, !41, null}
!809 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !810, file: !777, line: 119)
!810 = !DISubprogram(name: "sscanf", scope: !725, file: !725, line: 278, type: !811, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!811 = !DISubroutineType(types: !812)
!812 = !{!29, !41, !41, null}
!813 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !814, file: !777, line: 120)
!814 = !DISubprogram(name: "vfprintf", scope: !725, file: !725, line: 288, type: !815, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!815 = !DISubroutineType(types: !816)
!816 = !{!29, !784, !41, !817}
!817 = !DIDerivedType(tag: DW_TAG_typedef, name: "va_list", file: !818, line: 44, baseType: !819)
!818 = !DIFile(filename: "/Applications/Xcode_16.app/Contents/Developer/Platforms/MacOSX.platform/Developer/SDKs/MacOSX15.0.sdk/usr/include/sys/_types/_va_list.h", directory: "")
!819 = !DIDerivedType(tag: DW_TAG_typedef, name: "__darwin_va_list", file: !331, line: 95, baseType: !820)
!820 = !DIDerivedType(tag: DW_TAG_typedef, name: "__builtin_va_list", file: !3, baseType: !354)
!821 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !822, file: !777, line: 121)
!822 = !DISubprogram(name: "vfscanf", scope: !725, file: !725, line: 432, type: !815, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!823 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !824, file: !777, line: 122)
!824 = !DISubprogram(name: "vsscanf", scope: !725, file: !725, line: 435, type: !825, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!825 = !DISubroutineType(types: !826)
!826 = !{!29, !41, !41, !817}
!827 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !828, file: !777, line: 123)
!828 = !DISubprogram(name: "vsnprintf", scope: !725, file: !725, line: 434, type: !829, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!829 = !DISubroutineType(types: !830)
!830 = !{!29, !354, !328, !41, !817}
!831 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !832, file: !777, line: 124)
!832 = !DISubprogram(name: "vsprintf", scope: !725, file: !725, line: 295, type: !833, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!833 = !DISubroutineType(types: !834)
!834 = !{!29, !354, !41, !817}
!835 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !836, file: !777, line: 125)
!836 = !DISubprogram(name: "fgetc", scope: !725, file: !725, line: 234, type: !782, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!837 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !838, file: !777, line: 126)
!838 = !DISubprogram(name: "fgets", scope: !725, file: !725, line: 236, type: !839, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!839 = !DISubroutineType(types: !840)
!840 = !{!354, !354, !29, !784}
!841 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !842, file: !777, line: 127)
!842 = !DISubprogram(name: "fputc", scope: !725, file: !725, line: 243, type: !843, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!843 = !DISubroutineType(types: !844)
!844 = !{!29, !29, !784}
!845 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !846, file: !777, line: 128)
!846 = !DISubprogram(name: "fputs", linkageName: "\01_fputs", scope: !725, file: !725, line: 244, type: !847, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!847 = !DISubroutineType(types: !848)
!848 = !{!29, !41, !784}
!849 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !850, file: !777, line: 129)
!850 = !DISubprogram(name: "getc", scope: !725, file: !725, line: 253, type: !782, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!851 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !852, file: !777, line: 130)
!852 = !DISubprogram(name: "putc", scope: !725, file: !725, line: 262, type: !843, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!853 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !854, file: !777, line: 131)
!854 = !DISubprogram(name: "ungetc", scope: !725, file: !725, line: 287, type: !843, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!855 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !856, file: !777, line: 132)
!856 = !DISubprogram(name: "fread", scope: !725, file: !725, line: 245, type: !857, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!857 = !DISubroutineType(types: !858)
!858 = !{!328, !6, !328, !328, !784}
!859 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !860, file: !777, line: 133)
!860 = !DISubprogram(name: "fwrite", linkageName: "\01_fwrite", scope: !725, file: !725, line: 252, type: !861, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!861 = !DISubroutineType(types: !862)
!862 = !{!328, !72, !328, !328, !784}
!863 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !864, file: !777, line: 135)
!864 = !DISubprogram(name: "fgetpos", scope: !725, file: !725, line: 235, type: !865, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!865 = !DISubroutineType(types: !866)
!866 = !{!29, !784, !867}
!867 = !DIDerivedType(tag: DW_TAG_pointer_type, baseType: !753, size: 64)
!868 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !869, file: !777, line: 137)
!869 = !DISubprogram(name: "fseek", scope: !725, file: !725, line: 249, type: !870, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!870 = !DISubroutineType(types: !871)
!871 = !{!29, !784, !325, !29}
!872 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !873, file: !777, line: 139)
!873 = !DISubprogram(name: "fsetpos", scope: !725, file: !725, line: 250, type: !874, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!874 = !DISubroutineType(types: !875)
!875 = !{!29, !784, !876}
!876 = !DIDerivedType(tag: DW_TAG_pointer_type, baseType: !877, size: 64)
!877 = !DIDerivedType(tag: DW_TAG_const_type, baseType: !753)
!878 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !879, file: !777, line: 141)
!879 = !DISubprogram(name: "ftell", scope: !725, file: !725, line: 251, type: !880, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!880 = !DISubroutineType(types: !881)
!881 = !{!325, !784}
!882 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !883, file: !777, line: 142)
!883 = !DISubprogram(name: "rewind", scope: !725, file: !725, line: 267, type: !884, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!884 = !DISubroutineType(types: !885)
!885 = !{null, !784}
!886 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !887, file: !777, line: 143)
!887 = !DISubprogram(name: "clearerr", scope: !725, file: !725, line: 229, type: !884, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!888 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !889, file: !777, line: 144)
!889 = !DISubprogram(name: "feof", scope: !725, file: !725, line: 231, type: !782, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!890 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !891, file: !777, line: 145)
!891 = !DISubprogram(name: "ferror", scope: !725, file: !725, line: 232, type: !782, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!892 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !893, file: !777, line: 146)
!893 = !DISubprogram(name: "perror", scope: !725, file: !725, line: 261, type: !894, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!894 = !DISubroutineType(types: !895)
!895 = !{null, !41}
!896 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !897, file: !777, line: 149)
!897 = !DISubprogram(name: "fopen", linkageName: "\01_fopen", scope: !725, file: !725, line: 240, type: !898, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!898 = !DISubroutineType(types: !899)
!899 = !{!784, !41, !41}
!900 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !901, file: !777, line: 150)
!901 = !DISubprogram(name: "freopen", linkageName: "\01_freopen", scope: !725, file: !725, line: 246, type: !902, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!902 = !DISubroutineType(types: !903)
!903 = !{!784, !41, !41, !784}
!904 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !905, file: !777, line: 151)
!905 = !DISubprogram(name: "remove", scope: !725, file: !725, line: 265, type: !512, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!906 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !907, file: !777, line: 152)
!907 = !DISubprogram(name: "rename", scope: !725, file: !725, line: 266, type: !369, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!908 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !909, file: !777, line: 153)
!909 = !DISubprogram(name: "tmpfile", scope: !725, file: !725, line: 279, type: !910, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!910 = !DISubroutineType(types: !911)
!911 = !{!784}
!912 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !913, file: !777, line: 154)
!913 = !DISubprogram(name: "tmpnam", scope: !725, file: !725, line: 285, type: !914, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!914 = !DISubroutineType(types: !915)
!915 = !{!354, !354}
!916 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !917, file: !777, line: 158)
!917 = !DISubprogram(name: "getchar", scope: !725, file: !725, line: 254, type: !554, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!918 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !919, file: !777, line: 162)
!919 = !DISubprogram(name: "scanf", scope: !725, file: !725, line: 268, type: !920, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!920 = !DISubroutineType(types: !921)
!921 = !{!29, !41, null}
!922 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !923, file: !777, line: 163)
!923 = !DISubprogram(name: "vscanf", scope: !725, file: !725, line: 433, type: !924, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!924 = !DISubroutineType(types: !925)
!925 = !{!29, !41, !817}
!926 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !927, file: !777, line: 167)
!927 = !DISubprogram(name: "printf", scope: !928, file: !928, line: 31, type: !920, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!928 = !DIFile(filename: "/Applications/Xcode_16.app/Contents/Developer/Platforms/MacOSX.platform/Developer/SDKs/MacOSX15.0.sdk/usr/include/_printf.h", directory: "")
!929 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !930, file: !777, line: 168)
!930 = !DISubprogram(name: "putchar", scope: !725, file: !725, line: 263, type: !931, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!931 = !DISubroutineType(types: !932)
!932 = !{!29, !29}
!933 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !934, file: !777, line: 169)
!934 = !DISubprogram(name: "puts", scope: !725, file: !725, line: 264, type: !512, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!935 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !936, file: !777, line: 170)
!936 = !DISubprogram(name: "vprintf", scope: !725, file: !725, line: 289, type: !924, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!937 = !DIGlobalVariableExpression(var: !938, expr: !DIExpression())
!938 = distinct !DIGlobalVariable(name: "touchInitNode", scope: !939, file: !1204, line: 22, type: !1737, isLocal: false, isDefinition: true)
!939 = distinct !DICompileUnit(language: DW_LANG_C_plus_plus_14, file: !940, producer: "clang version 11.1.0 (https://github.com/apple/llvm-project 9205ffc7869a87cf3906b80dbd45b969c5794ef7)", isOptimized: true, runtimeVersion: 0, emissionKind: FullDebug, enums: !941, retainedTypes: !957, globals: !1201, imports: !1253, nameTableKind: None, sysroot: "/Applications/Xcode_16.app/Contents/Developer/Platforms/MacOSX.platform/Developer/SDKs/MacOSX15.0.sdk", sdk: "MacOSX15.0.sdk")
!940 = !DIFile(filename: "/Users/bytedance/KMP/kt2/kotlin_2.0/kotlin-native/runtime/src/compiler_interface/cpp/CompilerCInterface.cpp", directory: "/Users/bytedance/KMP/kt2/kotlin_2.0/kotlin-native/runtime/src")
!941 = !{!942, !948}
!942 = distinct !DICompositeType(tag: DW_TAG_enumeration_type, name: "MutexThreadStateHandling", scope: !944, file: !943, line: 31, baseType: !29, size: 32, flags: DIFlagEnumClass, elements: !945, identifier: "_ZTSN6kotlin24MutexThreadStateHandlingE")
!943 = !DIFile(filename: "main/cpp/concurrent/Mutex.hpp", directory: "/Users/bytedance/KMP/kt2/kotlin_2.0/kotlin-native/runtime/src")
!944 = !DINamespace(name: "kotlin", scope: null)
!945 = !{!946, !947}
!946 = !DIEnumerator(name: "kIgnore", value: 0)
!947 = !DIEnumerator(name: "kSwitchIfRegistered", value: 1)
!948 = distinct !DICompositeType(tag: DW_TAG_enumeration_type, name: "memory_order", scope: !321, file: !949, line: 644, baseType: !50, size: 32, elements: !950, identifier: "_ZTSNSt3__112memory_orderE")
!949 = !DIFile(filename: ".konan/dependencies/apple-llvm-20200714-macos-aarch64-1/bin/../include/c++/v1/atomic", directory: "/Users/bytedance")
!950 = !{!951, !952, !953, !954, !955, !956}
!951 = !DIEnumerator(name: "memory_order_relaxed", value: 0, isUnsigned: true)
!952 = !DIEnumerator(name: "memory_order_consume", value: 1, isUnsigned: true)
!953 = !DIEnumerator(name: "memory_order_acquire", value: 2, isUnsigned: true)
!954 = !DIEnumerator(name: "memory_order_release", value: 3, isUnsigned: true)
!955 = !DIEnumerator(name: "memory_order_acq_rel", value: 4, isUnsigned: true)
!956 = !DIEnumerator(name: "memory_order_seq_cst", value: 5, isUnsigned: true)
!957 = !{!6, !958}
!958 = !DIDerivedType(tag: DW_TAG_pointer_type, baseType: !959, size: 64)
!959 = distinct !DICompositeType(tag: DW_TAG_class_type, name: "RWSpinLock<kotlin::MutexThreadStateHandling::kIgnore>", scope: !944, file: !943, line: 92, size: 64, flags: DIFlagTypePassByReference | DIFlagNonTrivial, elements: !960, templateParams: !1199, identifier: "_ZTSN6kotlin10RWSpinLockILNS_24MutexThreadStateHandlingE0EEE")
!960 = !{!961, !986, !989, !1182, !1186, !1189, !1190, !1191, !1192, !1193, !1197, !1198}
!961 = !DIDerivedType(tag: DW_TAG_inheritance, scope: !959, baseType: !962, extraData: i32 0)
!962 = distinct !DICompositeType(tag: DW_TAG_class_type, name: "Pinned", scope: !944, file: !963, line: 46, size: 8, flags: DIFlagTypePassByReference, elements: !964, identifier: "_ZTSN6kotlin6PinnedE")
!963 = !DIFile(filename: "main/cpp/Utils.hpp", directory: "/Users/bytedance/KMP/kt2/kotlin_2.0/kotlin-native/runtime/src")
!964 = !{!965, !969, !974, !978, !982, !985}
!965 = !DISubprogram(name: "Pinned", scope: !962, file: !963, line: 49, type: !966, scopeLine: 49, flags: DIFlagProtected | DIFlagPrototyped, spFlags: DISPFlagOptimized)
!966 = !DISubroutineType(types: !967)
!967 = !{null, !968}
!968 = !DIDerivedType(tag: DW_TAG_pointer_type, baseType: !962, size: 64, flags: DIFlagArtificial | DIFlagObjectPointer)
!969 = !DISubprogram(name: "Pinned", scope: !962, file: !963, line: 50, type: !970, scopeLine: 50, flags: DIFlagProtected | DIFlagPrototyped, spFlags: DISPFlagOptimized | DISPFlagDeleted)
!970 = !DISubroutineType(types: !971)
!971 = !{null, !968, !972}
!972 = !DIDerivedType(tag: DW_TAG_reference_type, baseType: !973, size: 64)
!973 = !DIDerivedType(tag: DW_TAG_const_type, baseType: !962)
!974 = !DISubprogram(name: "Pinned", scope: !962, file: !963, line: 51, type: !975, scopeLine: 51, flags: DIFlagProtected | DIFlagPrototyped, spFlags: DISPFlagOptimized | DISPFlagDeleted)
!975 = !DISubroutineType(types: !976)
!976 = !{null, !968, !977}
!977 = !DIDerivedType(tag: DW_TAG_rvalue_reference_type, baseType: !962, size: 64)
!978 = !DISubprogram(name: "operator=", linkageName: "_ZN6kotlin6PinnedaSERKS0_", scope: !962, file: !963, line: 53, type: !979, scopeLine: 53, flags: DIFlagProtected | DIFlagPrototyped, spFlags: DISPFlagOptimized | DISPFlagDeleted)
!979 = !DISubroutineType(types: !980)
!980 = !{!981, !968, !972}
!981 = !DIDerivedType(tag: DW_TAG_reference_type, baseType: !962, size: 64)
!982 = !DISubprogram(name: "operator=", linkageName: "_ZN6kotlin6PinnedaSEOS0_", scope: !962, file: !963, line: 54, type: !983, scopeLine: 54, flags: DIFlagProtected | DIFlagPrototyped, spFlags: DISPFlagOptimized | DISPFlagDeleted)
!983 = !DISubroutineType(types: !984)
!984 = !{!981, !968, !977}
!985 = !DISubprogram(name: "~Pinned", scope: !962, file: !963, line: 58, type: !966, scopeLine: 58, flags: DIFlagProtected | DIFlagPrototyped, spFlags: DISPFlagOptimized)
!986 = !DIDerivedType(tag: DW_TAG_member, name: "kLocked", scope: !959, file: !943, line: 164, baseType: !987, flags: DIFlagStaticMember, extraData: i64 -1)
!987 = !DIDerivedType(tag: DW_TAG_const_type, baseType: !988)
!988 = !DIDerivedType(tag: DW_TAG_typedef, name: "State", scope: !959, file: !943, line: 93, baseType: !435)
!989 = !DIDerivedType(tag: DW_TAG_member, name: "state_", scope: !959, file: !943, line: 166, baseType: !990, size: 64)
!990 = distinct !DICompositeType(tag: DW_TAG_structure_type, name: "atomic<unsigned long long>", scope: !321, file: !949, line: 1773, size: 64, flags: DIFlagTypePassByReference, elements: !991, templateParams: !1013, identifier: "_ZTSNSt3__16atomicIyEE")
!991 = !{!992, !1167, !1171, !1174, !1179}
!992 = !DIDerivedType(tag: DW_TAG_inheritance, scope: !990, baseType: !993, extraData: i32 0)
!993 = distinct !DICompositeType(tag: DW_TAG_structure_type, name: "__atomic_base<unsigned long long, true>", scope: !321, file: !949, line: 1692, size: 64, flags: DIFlagTypePassByReference, elements: !994, templateParams: !1165, identifier: "_ZTSNSt3__113__atomic_baseIyLb1EEE")
!994 = !{!995, !1112, !1116, !1119, !1124, !1127, !1128, !1129, !1130, !1131, !1132, !1133, !1134, !1135, !1138, !1141, !1142, !1143, !1146, !1149, !1150, !1151, !1154, !1157, !1158, !1159, !1160, !1161, !1162, !1163, !1164}
!995 = !DIDerivedType(tag: DW_TAG_inheritance, scope: !993, baseType: !996, extraData: i32 0)
!996 = distinct !DICompositeType(tag: DW_TAG_structure_type, name: "__atomic_base<unsigned long long, false>", scope: !321, file: !949, line: 1573, size: 64, flags: DIFlagTypePassByReference, elements: !997, templateParams: !1110, identifier: "_ZTSNSt3__113__atomic_baseIyLb0EEE")
!997 = !{!998, !1024, !1026, !1032, !1037, !1042, !1046, !1049, !1052, !1055, !1058, !1061, !1064, !1068, !1071, !1072, !1073, !1076, !1079, !1080, !1081, !1084, !1087, !1090, !1093, !1094, !1095, !1096, !1099, !1103, !1107}
!998 = !DIDerivedType(tag: DW_TAG_member, name: "__a_", scope: !996, file: !949, line: 1575, baseType: !999, size: 64)
!999 = distinct !DICompositeType(tag: DW_TAG_structure_type, name: "__cxx_atomic_impl<unsigned long long, std::__1::__cxx_atomic_base_impl<unsigned long long> >", scope: !321, file: !949, line: 1471, size: 64, flags: DIFlagTypePassByValue, elements: !1000, templateParams: !1022, identifier: "_ZTSNSt3__117__cxx_atomic_implIyNS_22__cxx_atomic_base_implIyEEEE")
!1000 = !{!1001, !1015, !1019}
!1001 = !DIDerivedType(tag: DW_TAG_inheritance, scope: !999, baseType: !1002, extraData: i32 0)
!1002 = distinct !DICompositeType(tag: DW_TAG_structure_type, name: "__cxx_atomic_base_impl<unsigned long long>", scope: !321, file: !949, line: 941, size: 64, flags: DIFlagTypePassByValue, elements: !1003, templateParams: !1013, identifier: "_ZTSNSt3__122__cxx_atomic_base_implIyEE")
!1003 = !{!1004, !1006, !1010}
!1004 = !DIDerivedType(tag: DW_TAG_member, name: "__a_value", scope: !1002, file: !949, line: 951, baseType: !1005, size: 64)
!1005 = !DIDerivedType(tag: DW_TAG_atomic_type, baseType: !437)
!1006 = !DISubprogram(name: "__cxx_atomic_base_impl", scope: !1002, file: !949, line: 945, type: !1007, scopeLine: 945, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1007 = !DISubroutineType(types: !1008)
!1008 = !{null, !1009}
!1009 = !DIDerivedType(tag: DW_TAG_pointer_type, baseType: !1002, size: 64, flags: DIFlagArtificial | DIFlagObjectPointer)
!1010 = !DISubprogram(name: "__cxx_atomic_base_impl", scope: !1002, file: !949, line: 949, type: !1011, scopeLine: 949, flags: DIFlagExplicit | DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1011 = !DISubroutineType(types: !1012)
!1012 = !{null, !1009, !437}
!1013 = !{!1014}
!1014 = !DITemplateTypeParameter(name: "_Tp", type: !437)
!1015 = !DISubprogram(name: "__cxx_atomic_impl", scope: !999, file: !949, line: 1478, type: !1016, scopeLine: 1478, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1016 = !DISubroutineType(types: !1017)
!1017 = !{null, !1018}
!1018 = !DIDerivedType(tag: DW_TAG_pointer_type, baseType: !999, size: 64, flags: DIFlagArtificial | DIFlagObjectPointer)
!1019 = !DISubprogram(name: "__cxx_atomic_impl", scope: !999, file: !949, line: 1479, type: !1020, scopeLine: 1479, flags: DIFlagExplicit | DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1020 = !DISubroutineType(types: !1021)
!1021 = !{null, !1018, !437}
!1022 = !{!1014, !1023}
!1023 = !DITemplateTypeParameter(name: "_Base", type: !1002)
!1024 = !DIDerivedType(tag: DW_TAG_member, name: "is_always_lock_free", scope: !996, file: !949, line: 1578, baseType: !1025, flags: DIFlagStaticMember)
!1025 = !DIDerivedType(tag: DW_TAG_const_type, baseType: !98)
!1026 = !DISubprogram(name: "is_lock_free", linkageName: "_ZNVKSt3__113__atomic_baseIyLb0EE12is_lock_freeEv", scope: !996, file: !949, line: 1582, type: !1027, scopeLine: 1582, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1027 = !DISubroutineType(types: !1028)
!1028 = !{!98, !1029}
!1029 = !DIDerivedType(tag: DW_TAG_pointer_type, baseType: !1030, size: 64, flags: DIFlagArtificial | DIFlagObjectPointer)
!1030 = !DIDerivedType(tag: DW_TAG_const_type, baseType: !1031)
!1031 = !DIDerivedType(tag: DW_TAG_volatile_type, baseType: !996)
!1032 = !DISubprogram(name: "is_lock_free", linkageName: "_ZNKSt3__113__atomic_baseIyLb0EE12is_lock_freeEv", scope: !996, file: !949, line: 1585, type: !1033, scopeLine: 1585, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1033 = !DISubroutineType(types: !1034)
!1034 = !{!98, !1035}
!1035 = !DIDerivedType(tag: DW_TAG_pointer_type, baseType: !1036, size: 64, flags: DIFlagArtificial | DIFlagObjectPointer)
!1036 = !DIDerivedType(tag: DW_TAG_const_type, baseType: !996)
!1037 = !DISubprogram(name: "store", linkageName: "_ZNVSt3__113__atomic_baseIyLb0EE5storeEyNS_12memory_orderE", scope: !996, file: !949, line: 1588, type: !1038, scopeLine: 1588, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1038 = !DISubroutineType(types: !1039)
!1039 = !{null, !1040, !437, !1041}
!1040 = !DIDerivedType(tag: DW_TAG_pointer_type, baseType: !1031, size: 64, flags: DIFlagArtificial | DIFlagObjectPointer)
!1041 = !DIDerivedType(tag: DW_TAG_typedef, name: "memory_order", scope: !321, file: !949, line: 651, baseType: !948)
!1042 = !DISubprogram(name: "store", linkageName: "_ZNSt3__113__atomic_baseIyLb0EE5storeEyNS_12memory_orderE", scope: !996, file: !949, line: 1592, type: !1043, scopeLine: 1592, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1043 = !DISubroutineType(types: !1044)
!1044 = !{null, !1045, !437, !1041}
!1045 = !DIDerivedType(tag: DW_TAG_pointer_type, baseType: !996, size: 64, flags: DIFlagArtificial | DIFlagObjectPointer)
!1046 = !DISubprogram(name: "load", linkageName: "_ZNVKSt3__113__atomic_baseIyLb0EE4loadENS_12memory_orderE", scope: !996, file: !949, line: 1596, type: !1047, scopeLine: 1596, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1047 = !DISubroutineType(types: !1048)
!1048 = !{!437, !1029, !1041}
!1049 = !DISubprogram(name: "load", linkageName: "_ZNKSt3__113__atomic_baseIyLb0EE4loadENS_12memory_orderE", scope: !996, file: !949, line: 1600, type: !1050, scopeLine: 1600, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1050 = !DISubroutineType(types: !1051)
!1051 = !{!437, !1035, !1041}
!1052 = !DISubprogram(name: "operator unsigned long long", linkageName: "_ZNVKSt3__113__atomic_baseIyLb0EEcvyEv", scope: !996, file: !949, line: 1604, type: !1053, scopeLine: 1604, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1053 = !DISubroutineType(types: !1054)
!1054 = !{!437, !1029}
!1055 = !DISubprogram(name: "operator unsigned long long", linkageName: "_ZNKSt3__113__atomic_baseIyLb0EEcvyEv", scope: !996, file: !949, line: 1606, type: !1056, scopeLine: 1606, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1056 = !DISubroutineType(types: !1057)
!1057 = !{!437, !1035}
!1058 = !DISubprogram(name: "exchange", linkageName: "_ZNVSt3__113__atomic_baseIyLb0EE8exchangeEyNS_12memory_orderE", scope: !996, file: !949, line: 1608, type: !1059, scopeLine: 1608, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1059 = !DISubroutineType(types: !1060)
!1060 = !{!437, !1040, !437, !1041}
!1061 = !DISubprogram(name: "exchange", linkageName: "_ZNSt3__113__atomic_baseIyLb0EE8exchangeEyNS_12memory_orderE", scope: !996, file: !949, line: 1611, type: !1062, scopeLine: 1611, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1062 = !DISubroutineType(types: !1063)
!1063 = !{!437, !1045, !437, !1041}
!1064 = !DISubprogram(name: "compare_exchange_weak", linkageName: "_ZNVSt3__113__atomic_baseIyLb0EE21compare_exchange_weakERyyNS_12memory_orderES3_", scope: !996, file: !949, line: 1614, type: !1065, scopeLine: 1614, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1065 = !DISubroutineType(types: !1066)
!1066 = !{!98, !1040, !1067, !437, !1041, !1041}
!1067 = !DIDerivedType(tag: DW_TAG_reference_type, baseType: !437, size: 64)
!1068 = !DISubprogram(name: "compare_exchange_weak", linkageName: "_ZNSt3__113__atomic_baseIyLb0EE21compare_exchange_weakERyyNS_12memory_orderES3_", scope: !996, file: !949, line: 1619, type: !1069, scopeLine: 1619, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1069 = !DISubroutineType(types: !1070)
!1070 = !{!98, !1045, !1067, !437, !1041, !1041}
!1071 = !DISubprogram(name: "compare_exchange_strong", linkageName: "_ZNVSt3__113__atomic_baseIyLb0EE23compare_exchange_strongERyyNS_12memory_orderES3_", scope: !996, file: !949, line: 1624, type: !1065, scopeLine: 1624, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1072 = !DISubprogram(name: "compare_exchange_strong", linkageName: "_ZNSt3__113__atomic_baseIyLb0EE23compare_exchange_strongERyyNS_12memory_orderES3_", scope: !996, file: !949, line: 1629, type: !1069, scopeLine: 1629, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1073 = !DISubprogram(name: "compare_exchange_weak", linkageName: "_ZNVSt3__113__atomic_baseIyLb0EE21compare_exchange_weakERyyNS_12memory_orderE", scope: !996, file: !949, line: 1634, type: !1074, scopeLine: 1634, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1074 = !DISubroutineType(types: !1075)
!1075 = !{!98, !1040, !1067, !437, !1041}
!1076 = !DISubprogram(name: "compare_exchange_weak", linkageName: "_ZNSt3__113__atomic_baseIyLb0EE21compare_exchange_weakERyyNS_12memory_orderE", scope: !996, file: !949, line: 1638, type: !1077, scopeLine: 1638, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1077 = !DISubroutineType(types: !1078)
!1078 = !{!98, !1045, !1067, !437, !1041}
!1079 = !DISubprogram(name: "compare_exchange_strong", linkageName: "_ZNVSt3__113__atomic_baseIyLb0EE23compare_exchange_strongERyyNS_12memory_orderE", scope: !996, file: !949, line: 1642, type: !1074, scopeLine: 1642, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1080 = !DISubprogram(name: "compare_exchange_strong", linkageName: "_ZNSt3__113__atomic_baseIyLb0EE23compare_exchange_strongERyyNS_12memory_orderE", scope: !996, file: !949, line: 1646, type: !1077, scopeLine: 1646, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1081 = !DISubprogram(name: "wait", linkageName: "_ZNVKSt3__113__atomic_baseIyLb0EE4waitEyNS_12memory_orderE", scope: !996, file: !949, line: 1650, type: !1082, scopeLine: 1650, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1082 = !DISubroutineType(types: !1083)
!1083 = !{null, !1029, !437, !1041}
!1084 = !DISubprogram(name: "wait", linkageName: "_ZNKSt3__113__atomic_baseIyLb0EE4waitEyNS_12memory_orderE", scope: !996, file: !949, line: 1652, type: !1085, scopeLine: 1652, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1085 = !DISubroutineType(types: !1086)
!1086 = !{null, !1035, !437, !1041}
!1087 = !DISubprogram(name: "notify_one", linkageName: "_ZNVSt3__113__atomic_baseIyLb0EE10notify_oneEv", scope: !996, file: !949, line: 1654, type: !1088, scopeLine: 1654, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1088 = !DISubroutineType(types: !1089)
!1089 = !{null, !1040}
!1090 = !DISubprogram(name: "notify_one", linkageName: "_ZNSt3__113__atomic_baseIyLb0EE10notify_oneEv", scope: !996, file: !949, line: 1656, type: !1091, scopeLine: 1656, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1091 = !DISubroutineType(types: !1092)
!1092 = !{null, !1045}
!1093 = !DISubprogram(name: "notify_all", linkageName: "_ZNVSt3__113__atomic_baseIyLb0EE10notify_allEv", scope: !996, file: !949, line: 1658, type: !1088, scopeLine: 1658, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1094 = !DISubprogram(name: "notify_all", linkageName: "_ZNSt3__113__atomic_baseIyLb0EE10notify_allEv", scope: !996, file: !949, line: 1660, type: !1091, scopeLine: 1660, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1095 = !DISubprogram(name: "__atomic_base", scope: !996, file: !949, line: 1664, type: !1091, scopeLine: 1664, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1096 = !DISubprogram(name: "__atomic_base", scope: !996, file: !949, line: 1667, type: !1097, scopeLine: 1667, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1097 = !DISubroutineType(types: !1098)
!1098 = !{null, !1045, !437}
!1099 = !DISubprogram(name: "__atomic_base", scope: !996, file: !949, line: 1670, type: !1100, scopeLine: 1670, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized | DISPFlagDeleted)
!1100 = !DISubroutineType(types: !1101)
!1101 = !{null, !1045, !1102}
!1102 = !DIDerivedType(tag: DW_TAG_reference_type, baseType: !1036, size: 64)
!1103 = !DISubprogram(name: "operator=", linkageName: "_ZNSt3__113__atomic_baseIyLb0EEaSERKS1_", scope: !996, file: !949, line: 1671, type: !1104, scopeLine: 1671, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized | DISPFlagDeleted)
!1104 = !DISubroutineType(types: !1105)
!1105 = !{!1106, !1045, !1102}
!1106 = !DIDerivedType(tag: DW_TAG_reference_type, baseType: !996, size: 64)
!1107 = !DISubprogram(name: "operator=", linkageName: "_ZNVSt3__113__atomic_baseIyLb0EEaSERKS1_", scope: !996, file: !949, line: 1672, type: !1108, scopeLine: 1672, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized | DISPFlagDeleted)
!1108 = !DISubroutineType(types: !1109)
!1109 = !{!1106, !1040, !1102}
!1110 = !{!1014, !1111}
!1111 = !DITemplateValueParameter(type: !98, value: i1 false)
!1112 = !DISubprogram(name: "__atomic_base", scope: !993, file: !949, line: 1697, type: !1113, scopeLine: 1697, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1113 = !DISubroutineType(types: !1114)
!1114 = !{null, !1115}
!1115 = !DIDerivedType(tag: DW_TAG_pointer_type, baseType: !993, size: 64, flags: DIFlagArtificial | DIFlagObjectPointer)
!1116 = !DISubprogram(name: "__atomic_base", scope: !993, file: !949, line: 1699, type: !1117, scopeLine: 1699, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1117 = !DISubroutineType(types: !1118)
!1118 = !{null, !1115, !437}
!1119 = !DISubprogram(name: "fetch_add", linkageName: "_ZNVSt3__113__atomic_baseIyLb1EE9fetch_addEyNS_12memory_orderE", scope: !993, file: !949, line: 1702, type: !1120, scopeLine: 1702, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1120 = !DISubroutineType(types: !1121)
!1121 = !{!437, !1122, !437, !1041}
!1122 = !DIDerivedType(tag: DW_TAG_pointer_type, baseType: !1123, size: 64, flags: DIFlagArtificial | DIFlagObjectPointer)
!1123 = !DIDerivedType(tag: DW_TAG_volatile_type, baseType: !993)
!1124 = !DISubprogram(name: "fetch_add", linkageName: "_ZNSt3__113__atomic_baseIyLb1EE9fetch_addEyNS_12memory_orderE", scope: !993, file: !949, line: 1705, type: !1125, scopeLine: 1705, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1125 = !DISubroutineType(types: !1126)
!1126 = !{!437, !1115, !437, !1041}
!1127 = !DISubprogram(name: "fetch_sub", linkageName: "_ZNVSt3__113__atomic_baseIyLb1EE9fetch_subEyNS_12memory_orderE", scope: !993, file: !949, line: 1708, type: !1120, scopeLine: 1708, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1128 = !DISubprogram(name: "fetch_sub", linkageName: "_ZNSt3__113__atomic_baseIyLb1EE9fetch_subEyNS_12memory_orderE", scope: !993, file: !949, line: 1711, type: !1125, scopeLine: 1711, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1129 = !DISubprogram(name: "fetch_and", linkageName: "_ZNVSt3__113__atomic_baseIyLb1EE9fetch_andEyNS_12memory_orderE", scope: !993, file: !949, line: 1714, type: !1120, scopeLine: 1714, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1130 = !DISubprogram(name: "fetch_and", linkageName: "_ZNSt3__113__atomic_baseIyLb1EE9fetch_andEyNS_12memory_orderE", scope: !993, file: !949, line: 1717, type: !1125, scopeLine: 1717, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1131 = !DISubprogram(name: "fetch_or", linkageName: "_ZNVSt3__113__atomic_baseIyLb1EE8fetch_orEyNS_12memory_orderE", scope: !993, file: !949, line: 1720, type: !1120, scopeLine: 1720, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1132 = !DISubprogram(name: "fetch_or", linkageName: "_ZNSt3__113__atomic_baseIyLb1EE8fetch_orEyNS_12memory_orderE", scope: !993, file: !949, line: 1723, type: !1125, scopeLine: 1723, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1133 = !DISubprogram(name: "fetch_xor", linkageName: "_ZNVSt3__113__atomic_baseIyLb1EE9fetch_xorEyNS_12memory_orderE", scope: !993, file: !949, line: 1726, type: !1120, scopeLine: 1726, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1134 = !DISubprogram(name: "fetch_xor", linkageName: "_ZNSt3__113__atomic_baseIyLb1EE9fetch_xorEyNS_12memory_orderE", scope: !993, file: !949, line: 1729, type: !1125, scopeLine: 1729, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1135 = !DISubprogram(name: "operator++", linkageName: "_ZNVSt3__113__atomic_baseIyLb1EEppEi", scope: !993, file: !949, line: 1733, type: !1136, scopeLine: 1733, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1136 = !DISubroutineType(types: !1137)
!1137 = !{!437, !1122, !29}
!1138 = !DISubprogram(name: "operator++", linkageName: "_ZNSt3__113__atomic_baseIyLb1EEppEi", scope: !993, file: !949, line: 1735, type: !1139, scopeLine: 1735, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1139 = !DISubroutineType(types: !1140)
!1140 = !{!437, !1115, !29}
!1141 = !DISubprogram(name: "operator--", linkageName: "_ZNVSt3__113__atomic_baseIyLb1EEmmEi", scope: !993, file: !949, line: 1737, type: !1136, scopeLine: 1737, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1142 = !DISubprogram(name: "operator--", linkageName: "_ZNSt3__113__atomic_baseIyLb1EEmmEi", scope: !993, file: !949, line: 1739, type: !1139, scopeLine: 1739, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1143 = !DISubprogram(name: "operator++", linkageName: "_ZNVSt3__113__atomic_baseIyLb1EEppEv", scope: !993, file: !949, line: 1741, type: !1144, scopeLine: 1741, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1144 = !DISubroutineType(types: !1145)
!1145 = !{!437, !1122}
!1146 = !DISubprogram(name: "operator++", linkageName: "_ZNSt3__113__atomic_baseIyLb1EEppEv", scope: !993, file: !949, line: 1743, type: !1147, scopeLine: 1743, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1147 = !DISubroutineType(types: !1148)
!1148 = !{!437, !1115}
!1149 = !DISubprogram(name: "operator--", linkageName: "_ZNVSt3__113__atomic_baseIyLb1EEmmEv", scope: !993, file: !949, line: 1745, type: !1144, scopeLine: 1745, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1150 = !DISubprogram(name: "operator--", linkageName: "_ZNSt3__113__atomic_baseIyLb1EEmmEv", scope: !993, file: !949, line: 1747, type: !1147, scopeLine: 1747, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1151 = !DISubprogram(name: "operator+=", linkageName: "_ZNVSt3__113__atomic_baseIyLb1EEpLEy", scope: !993, file: !949, line: 1749, type: !1152, scopeLine: 1749, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1152 = !DISubroutineType(types: !1153)
!1153 = !{!437, !1122, !437}
!1154 = !DISubprogram(name: "operator+=", linkageName: "_ZNSt3__113__atomic_baseIyLb1EEpLEy", scope: !993, file: !949, line: 1751, type: !1155, scopeLine: 1751, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1155 = !DISubroutineType(types: !1156)
!1156 = !{!437, !1115, !437}
!1157 = !DISubprogram(name: "operator-=", linkageName: "_ZNVSt3__113__atomic_baseIyLb1EEmIEy", scope: !993, file: !949, line: 1753, type: !1152, scopeLine: 1753, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1158 = !DISubprogram(name: "operator-=", linkageName: "_ZNSt3__113__atomic_baseIyLb1EEmIEy", scope: !993, file: !949, line: 1755, type: !1155, scopeLine: 1755, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1159 = !DISubprogram(name: "operator&=", linkageName: "_ZNVSt3__113__atomic_baseIyLb1EEaNEy", scope: !993, file: !949, line: 1757, type: !1152, scopeLine: 1757, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1160 = !DISubprogram(name: "operator&=", linkageName: "_ZNSt3__113__atomic_baseIyLb1EEaNEy", scope: !993, file: !949, line: 1759, type: !1155, scopeLine: 1759, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1161 = !DISubprogram(name: "operator|=", linkageName: "_ZNVSt3__113__atomic_baseIyLb1EEoREy", scope: !993, file: !949, line: 1761, type: !1152, scopeLine: 1761, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1162 = !DISubprogram(name: "operator|=", linkageName: "_ZNSt3__113__atomic_baseIyLb1EEoREy", scope: !993, file: !949, line: 1763, type: !1155, scopeLine: 1763, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1163 = !DISubprogram(name: "operator^=", linkageName: "_ZNVSt3__113__atomic_baseIyLb1EEeOEy", scope: !993, file: !949, line: 1765, type: !1152, scopeLine: 1765, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1164 = !DISubprogram(name: "operator^=", linkageName: "_ZNSt3__113__atomic_baseIyLb1EEeOEy", scope: !993, file: !949, line: 1767, type: !1155, scopeLine: 1767, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1165 = !{!1014, !1166}
!1166 = !DITemplateValueParameter(type: !98, value: i1 true)
!1167 = !DISubprogram(name: "atomic", scope: !990, file: !949, line: 1779, type: !1168, scopeLine: 1779, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1168 = !DISubroutineType(types: !1169)
!1169 = !{null, !1170}
!1170 = !DIDerivedType(tag: DW_TAG_pointer_type, baseType: !990, size: 64, flags: DIFlagArtificial | DIFlagObjectPointer)
!1171 = !DISubprogram(name: "atomic", scope: !990, file: !949, line: 1781, type: !1172, scopeLine: 1781, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1172 = !DISubroutineType(types: !1173)
!1173 = !{null, !1170, !437}
!1174 = !DISubprogram(name: "operator=", linkageName: "_ZNVSt3__16atomicIyEaSEy", scope: !990, file: !949, line: 1784, type: !1175, scopeLine: 1784, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1175 = !DISubroutineType(types: !1176)
!1176 = !{!437, !1177, !437}
!1177 = !DIDerivedType(tag: DW_TAG_pointer_type, baseType: !1178, size: 64, flags: DIFlagArtificial | DIFlagObjectPointer)
!1178 = !DIDerivedType(tag: DW_TAG_volatile_type, baseType: !990)
!1179 = !DISubprogram(name: "operator=", linkageName: "_ZNSt3__16atomicIyEaSEy", scope: !990, file: !949, line: 1787, type: !1180, scopeLine: 1787, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1180 = !DISubroutineType(types: !1181)
!1181 = !{!437, !1170, !437}
!1182 = !DISubprogram(name: "lock", linkageName: "_ZN6kotlin10RWSpinLockILNS_24MutexThreadStateHandlingE0EE4lockEv", scope: !959, file: !943, line: 96, type: !1183, scopeLine: 96, flags: DIFlagPublic | DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1183 = !DISubroutineType(types: !1184)
!1184 = !{null, !1185}
!1185 = !DIDerivedType(tag: DW_TAG_pointer_type, baseType: !959, size: 64, flags: DIFlagArtificial | DIFlagObjectPointer)
!1186 = !DISubprogram(name: "try_lock", linkageName: "_ZN6kotlin10RWSpinLockILNS_24MutexThreadStateHandlingE0EE8try_lockEv", scope: !959, file: !943, line: 110, type: !1187, scopeLine: 110, flags: DIFlagPublic | DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1187 = !DISubroutineType(types: !1188)
!1188 = !{!98, !1185}
!1189 = !DISubprogram(name: "unlock", linkageName: "_ZN6kotlin10RWSpinLockILNS_24MutexThreadStateHandlingE0EE6unlockEv", scope: !959, file: !943, line: 115, type: !1183, scopeLine: 115, flags: DIFlagPublic | DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1190 = !DISubprogram(name: "lock_shared", linkageName: "_ZN6kotlin10RWSpinLockILNS_24MutexThreadStateHandlingE0EE11lock_sharedEv", scope: !959, file: !943, line: 121, type: !1183, scopeLine: 121, flags: DIFlagPublic | DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1191 = !DISubprogram(name: "try_lock_shared", linkageName: "_ZN6kotlin10RWSpinLockILNS_24MutexThreadStateHandlingE0EE15try_lock_sharedEv", scope: !959, file: !943, line: 131, type: !1187, scopeLine: 131, flags: DIFlagPublic | DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1192 = !DISubprogram(name: "unlock_shared", linkageName: "_ZN6kotlin10RWSpinLockILNS_24MutexThreadStateHandlingE0EE13unlock_sharedEv", scope: !959, file: !943, line: 136, type: !1183, scopeLine: 136, flags: DIFlagPublic | DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1193 = !DISubprogram(name: "try_lock_impl", linkageName: "_ZN6kotlin10RWSpinLockILNS_24MutexThreadStateHandlingE0EE13try_lock_implERy", scope: !959, file: !943, line: 139, type: !1194, scopeLine: 139, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1194 = !DISubroutineType(types: !1195)
!1195 = !{!98, !1185, !1196}
!1196 = !DIDerivedType(tag: DW_TAG_reference_type, baseType: !988, size: 64)
!1197 = !DISubprogram(name: "try_lock_shared_impl", linkageName: "_ZN6kotlin10RWSpinLockILNS_24MutexThreadStateHandlingE0EE20try_lock_shared_implERy", scope: !959, file: !943, line: 150, type: !1194, scopeLine: 150, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1198 = !DISubprogram(name: "yield", linkageName: "_ZN6kotlin10RWSpinLockILNS_24MutexThreadStateHandlingE0EE5yieldEv", scope: !959, file: !943, line: 162, type: !195, scopeLine: 162, flags: DIFlagPrototyped | DIFlagStaticMember, spFlags: DISPFlagOptimized)
!1199 = !{!1200}
!1200 = !DITemplateValueParameter(name: "threadStateHandling", type: !942, value: i32 0)
!1201 = !{!937, !1202, !1205, !1207, !1209, !1211, !1213, !1215, !1223}
!1202 = !DIGlobalVariableExpression(var: !1203, expr: !DIExpression())
!1203 = distinct !DIGlobalVariable(name: "touchTypeInfo", scope: !939, file: !1204, line: 24, type: !17, isLocal: false, isDefinition: true)
!1204 = !DIFile(filename: "compiler_interface/cpp/CompilerCInterface.cpp", directory: "/Users/bytedance/KMP/kt2/kotlin_2.0/kotlin-native/runtime/src")
!1205 = !DIGlobalVariableExpression(var: !1206, expr: !DIExpression())
!1206 = distinct !DIGlobalVariable(name: "touchExtendedTypeInfo", scope: !939, file: !1204, line: 25, type: !24, isLocal: false, isDefinition: true)
!1207 = !DIGlobalVariableExpression(var: !1208, expr: !DIExpression())
!1208 = distinct !DIGlobalVariable(name: "touchInterfaceTableRecord", scope: !939, file: !1204, line: 26, type: !63, isLocal: false, isDefinition: true)
!1209 = !DIGlobalVariableExpression(var: !1210, expr: !DIExpression())
!1210 = distinct !DIGlobalVariable(name: "touchAssociatedObjectTableRecord", scope: !939, file: !1204, line: 27, type: !217, isLocal: false, isDefinition: true)
!1211 = !DIGlobalVariableExpression(var: !1212, expr: !DIExpression())
!1212 = distinct !DIGlobalVariable(name: "touchObjHeader", scope: !939, file: !1204, line: 29, type: !76, isLocal: false, isDefinition: true)
!1213 = !DIGlobalVariableExpression(var: !1214, expr: !DIExpression())
!1214 = distinct !DIGlobalVariable(name: "touchArrayHeader", scope: !939, file: !1204, line: 30, type: !126, isLocal: false, isDefinition: true)
!1215 = !DIGlobalVariableExpression(var: !1216, expr: !DIExpression())
!1216 = distinct !DIGlobalVariable(name: "touchFrameOverlay", scope: !939, file: !1204, line: 31, type: !1217, isLocal: false, isDefinition: true)
!1217 = distinct !DICompositeType(tag: DW_TAG_structure_type, name: "FrameOverlay", file: !77, line: 353, size: 128, flags: DIFlagTypePassByValue, elements: !1218, identifier: "_ZTS12FrameOverlay")
!1218 = !{!1219, !1221, !1222}
!1219 = !DIDerivedType(tag: DW_TAG_member, name: "previous", scope: !1217, file: !77, line: 354, baseType: !1220, size: 64)
!1220 = !DIDerivedType(tag: DW_TAG_pointer_type, baseType: !1217, size: 64)
!1221 = !DIDerivedType(tag: DW_TAG_member, name: "parameters", scope: !1217, file: !77, line: 356, baseType: !27, size: 32, offset: 64)
!1222 = !DIDerivedType(tag: DW_TAG_member, name: "count", scope: !1217, file: !77, line: 357, baseType: !27, size: 32, offset: 96)
!1223 = !DIGlobalVariableExpression(var: !1224, expr: !DIExpression())
!1224 = distinct !DIGlobalVariable(name: "touchKRefSharedHolder", scope: !939, file: !1204, line: 33, type: !1225, isLocal: false, isDefinition: true)
!1225 = distinct !DICompositeType(tag: DW_TAG_class_type, name: "KRefSharedHolder", file: !1226, line: 23, size: 128, flags: DIFlagTypePassByValue, elements: !1227, identifier: "_ZTS16KRefSharedHolder")
!1226 = !DIFile(filename: "main/cpp/MemorySharedRefs.hpp", directory: "/Users/bytedance/KMP/kt2/kotlin_2.0/kotlin-native/runtime/src")
!1227 = !{!1228, !1229, !1240, !1244, !1245, !1248}
!1228 = !DIDerivedType(tag: DW_TAG_member, name: "obj_", scope: !1225, file: !1226, line: 38, baseType: !75, size: 64)
!1229 = !DIDerivedType(tag: DW_TAG_member, scope: !1225, file: !1226, line: 39, baseType: !1230, size: 64, offset: 64)
!1230 = distinct !DICompositeType(tag: DW_TAG_union_type, scope: !1225, file: !1226, line: 39, size: 64, flags: DIFlagExportSymbols | DIFlagTypePassByValue, elements: !1231, identifier: "_ZTSN16KRefSharedHolderUt_E")
!1231 = !{!1232, !1236}
!1232 = !DIDerivedType(tag: DW_TAG_member, name: "context_", scope: !1230, file: !1226, line: 40, baseType: !1233, size: 64)
!1233 = !DIDerivedType(tag: DW_TAG_typedef, name: "ForeignRefContext", file: !77, line: 158, baseType: !1234)
!1234 = !DIDerivedType(tag: DW_TAG_pointer_type, baseType: !1235, size: 64)
!1235 = distinct !DICompositeType(tag: DW_TAG_class_type, name: "ForeignRefManager", file: !77, line: 157, flags: DIFlagFwdDecl | DIFlagNonTrivial, identifier: "_ZTS17ForeignRefManager")
!1236 = !DIDerivedType(tag: DW_TAG_member, name: "ref_", scope: !1230, file: !1226, line: 41, baseType: !1237, size: 64)
!1237 = !DIDerivedType(tag: DW_TAG_pointer_type, baseType: !1238, size: 64)
!1238 = distinct !DICompositeType(tag: DW_TAG_structure_type, name: "RawSpecialRef", scope: !1239, file: !77, line: 162, flags: DIFlagFwdDecl | DIFlagNonTrivial, identifier: "_ZTSN6kotlin2mm13RawSpecialRefE")
!1239 = !DINamespace(name: "mm", scope: !944)
!1240 = !DISubprogram(name: "initLocal", linkageName: "_ZN16KRefSharedHolder9initLocalEP9ObjHeader", scope: !1225, file: !1226, line: 25, type: !1241, scopeLine: 25, flags: DIFlagPublic | DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1241 = !DISubroutineType(types: !1242)
!1242 = !{null, !1243, !75}
!1243 = !DIDerivedType(tag: DW_TAG_pointer_type, baseType: !1225, size: 64, flags: DIFlagArtificial | DIFlagObjectPointer)
!1244 = !DISubprogram(name: "init", linkageName: "_ZN16KRefSharedHolder4initEP9ObjHeader", scope: !1225, file: !1226, line: 27, type: !1241, scopeLine: 27, flags: DIFlagPublic | DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1245 = !DISubprogram(name: "dispose", linkageName: "_ZN16KRefSharedHolder7disposeEv", scope: !1225, file: !1226, line: 33, type: !1246, scopeLine: 33, flags: DIFlagPublic | DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1246 = !DISubroutineType(types: !1247)
!1247 = !{null, !1243}
!1248 = !DISubprogram(name: "describe", linkageName: "_ZNK16KRefSharedHolder8describeEPP9ObjHeader", scope: !1225, file: !1226, line: 35, type: !1249, scopeLine: 35, flags: DIFlagPublic | DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1249 = !DISubroutineType(types: !1250)
!1250 = !{!75, !1251, !224}
!1251 = !DIDerivedType(tag: DW_TAG_pointer_type, baseType: !1252, size: 64, flags: DIFlagArtificial | DIFlagObjectPointer)
!1252 = !DIDerivedType(tag: DW_TAG_const_type, baseType: !1225)
!1253 = !{!320, !1254, !332, !1256, !1257, !1261, !350, !1263, !359, !1267, !1269, !367, !1273, !375, !1277, !1281, !386, !1285, !394, !396, !1289, !400, !402, !1291, !406, !1293, !414, !419, !423, !424, !428, !429, !433, !434, !438, !441, !443, !445, !447, !449, !451, !453, !455, !457, !459, !461, !463, !465, !467, !469, !471, !475, !478, !481, !1297, !1298, !486, !493, !499, !505, !510, !514, !518, !522, !527, !532, !536, !540, !544, !548, !552, !556, !1299, !565, !1303, !1307, !575, !578, !582, !1311, !589, !593, !1313, !1317, !606, !611, !615, !619, !623, !627, !1321, !1325, !639, !1329, !1333, !653, !1337, !659, !663, !678, !682, !686, !691, !696, !702, !708, !712, !1338, !718, !723, !778, !1342, !780, !785, !787, !1343, !795, !799, !1347, !805, !809, !1351, !1358, !1360, !1364, !1368, !835, !837, !841, !845, !849, !851, !853, !1372, !1376, !863, !868, !872, !878, !882, !886, !888, !890, !892, !896, !900, !904, !906, !908, !912, !916, !918, !1380, !926, !929, !933, !1384, !1386, !1394, !1398, !1402, !1406, !1410, !1415, !1419, !1423, !1425, !1427, !1429, !1431, !1433, !1435, !1437, !1439, !1441, !1443, !1445, !1447, !1449, !1454, !1459, !1464, !1469, !1471, !1474, !1476, !1478, !1480, !1482, !1484, !1486, !1488, !1490, !1492, !1496, !1500, !1504, !1506, !1510, !1514, !1527, !1528, !1529, !1530, !1531, !1536, !1538, !1542, !1546, !1550, !1554, !1556, !1560, !1564, !1568, !1572, !1576, !1580, !1582, !1584, !1588, !1592, !1596, !1600, !1604, !1608, !1612, !1616, !1620, !1624, !1626, !1628, !1632, !1634, !1638, !1642, !1647, !1649, !1651, !1653, !1657, !1661, !1665, !1667, !1671, !1673, !1675, !1677, !1679, !1683, !1687, !1689, !1695, !1700, !1704, !1708, !1712, !1717, !1721, !1725, !1729, !1733, !1735}
!1254 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !1255, file: !326, line: 50)
!1255 = !DIDerivedType(tag: DW_TAG_typedef, name: "size_t", file: !324, line: 46, baseType: !305)
!1256 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !1255, file: !342, line: 68)
!1257 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !1258, file: !342, line: 69)
!1258 = !DISubprogram(name: "memcpy", scope: !345, file: !345, line: 72, type: !1259, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1259 = !DISubroutineType(types: !1260)
!1260 = !{!6, !6, !72, !1255}
!1261 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !1262, file: !342, line: 70)
!1262 = !DISubprogram(name: "memmove", scope: !345, file: !345, line: 73, type: !1259, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1263 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !1264, file: !342, line: 72)
!1264 = !DISubprogram(name: "strncpy", scope: !345, file: !345, line: 85, type: !1265, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1265 = !DISubroutineType(types: !1266)
!1266 = !{!354, !354, !41, !1255}
!1267 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !1268, file: !342, line: 74)
!1268 = !DISubprogram(name: "strncat", scope: !345, file: !345, line: 83, type: !1265, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1269 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !1270, file: !342, line: 75)
!1270 = !DISubprogram(name: "memcmp", scope: !345, file: !345, line: 71, type: !1271, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1271 = !DISubroutineType(types: !1272)
!1272 = !{!29, !72, !72, !1255}
!1273 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !1274, file: !342, line: 77)
!1274 = !DISubprogram(name: "strncmp", scope: !345, file: !345, line: 84, type: !1275, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1275 = !DISubroutineType(types: !1276)
!1276 = !{!29, !41, !41, !1255}
!1277 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !1278, file: !342, line: 79)
!1278 = !DISubprogram(name: "strxfrm", scope: !345, file: !345, line: 91, type: !1279, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1279 = !DISubroutineType(types: !1280)
!1280 = !{!1255, !354, !41, !1255}
!1281 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !1282, file: !342, line: 80)
!1282 = !DISubprogram(name: "memchr", linkageName: "_Z6memchrUa9enable_ifIXLb1EEEPvim", scope: !383, file: !383, line: 98, type: !1283, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1283 = !DISubroutineType(types: !1284)
!1284 = !{!6, !6, !29, !1255}
!1285 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !1286, file: !342, line: 82)
!1286 = !DISubprogram(name: "strcspn", scope: !345, file: !345, line: 80, type: !1287, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1287 = !DISubroutineType(types: !1288)
!1288 = !{!1255, !41, !41}
!1289 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !1290, file: !342, line: 85)
!1290 = !DISubprogram(name: "strspn", scope: !345, file: !345, line: 88, type: !1287, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1291 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !1292, file: !342, line: 90)
!1292 = !DISubprogram(name: "memset", scope: !345, file: !345, line: 74, type: !1283, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1293 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !1294, file: !342, line: 92)
!1294 = !DISubprogram(name: "strlen", scope: !345, file: !345, line: 82, type: !1295, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1295 = !DISubroutineType(types: !1296)
!1296 = !{!1255, !41}
!1297 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !939, entity: !337, file: !340, line: 51)
!1298 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !1255, file: !485, line: 99)
!1299 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !1300, file: !485, line: 124)
!1300 = !DISubprogram(name: "calloc", scope: !562, file: !562, line: 55, type: !1301, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1301 = !DISubroutineType(types: !1302)
!1302 = !{!6, !1255, !1255}
!1303 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !1304, file: !485, line: 126)
!1304 = !DISubprogram(name: "malloc", scope: !562, file: !562, line: 54, type: !1305, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1305 = !DISubroutineType(types: !1306)
!1306 = !{!6, !1255}
!1307 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !1308, file: !485, line: 127)
!1308 = !DISubprogram(name: "realloc", scope: !562, file: !562, line: 57, type: !1309, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1309 = !DISubroutineType(types: !1310)
!1310 = !{!6, !6, !1255}
!1311 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !1312, file: !485, line: 131)
!1312 = !DISubprogram(name: "_Exit", scope: !488, file: !488, line: 191, type: !584, flags: DIFlagPrototyped | DIFlagNoReturn, spFlags: DISPFlagOptimized)
!1313 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !1314, file: !485, line: 136)
!1314 = !DISubprogram(name: "bsearch", scope: !488, file: !488, line: 137, type: !1315, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1315 = !DISubroutineType(types: !1316)
!1316 = !{!6, !72, !72, !1255, !1255, !599}
!1317 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !1318, file: !485, line: 137)
!1318 = !DISubprogram(name: "qsort", scope: !488, file: !488, line: 156, type: !1319, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1319 = !DISubroutineType(types: !1320)
!1320 = !{null, !6, !1255, !1255, !599}
!1321 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !1322, file: !485, line: 148)
!1322 = !DISubprogram(name: "mblen", scope: !488, file: !488, line: 152, type: !1323, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1323 = !DISubroutineType(types: !1324)
!1324 = !{!29, !41, !1255}
!1325 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !1326, file: !485, line: 149)
!1326 = !DISubprogram(name: "mbtowc", scope: !488, file: !488, line: 154, type: !1327, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1327 = !DISubroutineType(types: !1328)
!1328 = !{!29, !637, !41, !1255}
!1329 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !1330, file: !485, line: 151)
!1330 = !DISubprogram(name: "mbstowcs", scope: !488, file: !488, line: 153, type: !1331, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1331 = !DISubroutineType(types: !1332)
!1332 = !{!1255, !637, !41, !1255}
!1333 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !1334, file: !485, line: 152)
!1334 = !DISubprogram(name: "wcstombs", scope: !488, file: !488, line: 187, type: !1335, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1335 = !DISubroutineType(types: !1336)
!1336 = !{!1255, !354, !651, !1255}
!1337 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !1255, file: !657, line: 58)
!1338 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !1339, file: !657, line: 74)
!1339 = !DISubprogram(name: "strftime", linkageName: "\01_strftime", scope: !665, file: !665, line: 116, type: !1340, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1340 = !DISubroutineType(types: !1341)
!1341 = !{!1255, !354, !1255, !41, !700}
!1342 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !1255, file: !777, line: 109)
!1343 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !1344, file: !777, line: 114)
!1344 = !DISubprogram(name: "setvbuf", scope: !725, file: !725, line: 270, type: !1345, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1345 = !DISubroutineType(types: !1346)
!1346 = !{!29, !784, !354, !29, !1255}
!1347 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !1348, file: !777, line: 117)
!1348 = !DISubprogram(name: "snprintf", scope: !725, file: !725, line: 431, type: !1349, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1349 = !DISubroutineType(types: !1350)
!1350 = !{!29, !354, !1255, !41, null}
!1351 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !1352, file: !777, line: 120)
!1352 = !DISubprogram(name: "vfprintf", scope: !725, file: !725, line: 288, type: !1353, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1353 = !DISubroutineType(types: !1354)
!1354 = !{!29, !784, !41, !1355}
!1355 = !DIDerivedType(tag: DW_TAG_typedef, name: "va_list", file: !818, line: 44, baseType: !1356)
!1356 = !DIDerivedType(tag: DW_TAG_typedef, name: "__darwin_va_list", file: !331, line: 95, baseType: !1357)
!1357 = !DIDerivedType(tag: DW_TAG_typedef, name: "__builtin_va_list", file: !940, baseType: !354)
!1358 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !1359, file: !777, line: 121)
!1359 = !DISubprogram(name: "vfscanf", scope: !725, file: !725, line: 432, type: !1353, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1360 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !1361, file: !777, line: 122)
!1361 = !DISubprogram(name: "vsscanf", scope: !725, file: !725, line: 435, type: !1362, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1362 = !DISubroutineType(types: !1363)
!1363 = !{!29, !41, !41, !1355}
!1364 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !1365, file: !777, line: 123)
!1365 = !DISubprogram(name: "vsnprintf", scope: !725, file: !725, line: 434, type: !1366, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1366 = !DISubroutineType(types: !1367)
!1367 = !{!29, !354, !1255, !41, !1355}
!1368 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !1369, file: !777, line: 124)
!1369 = !DISubprogram(name: "vsprintf", scope: !725, file: !725, line: 295, type: !1370, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1370 = !DISubroutineType(types: !1371)
!1371 = !{!29, !354, !41, !1355}
!1372 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !1373, file: !777, line: 132)
!1373 = !DISubprogram(name: "fread", scope: !725, file: !725, line: 245, type: !1374, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1374 = !DISubroutineType(types: !1375)
!1375 = !{!1255, !6, !1255, !1255, !784}
!1376 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !1377, file: !777, line: 133)
!1377 = !DISubprogram(name: "fwrite", linkageName: "\01_fwrite", scope: !725, file: !725, line: 252, type: !1378, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1378 = !DISubroutineType(types: !1379)
!1379 = !{!1255, !72, !1255, !1255, !784}
!1380 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !1381, file: !777, line: 163)
!1381 = !DISubprogram(name: "vscanf", scope: !725, file: !725, line: 433, type: !1382, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1382 = !DISubroutineType(types: !1383)
!1383 = !{!29, !41, !1355}
!1384 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !1385, file: !777, line: 170)
!1385 = !DISubprogram(name: "vprintf", scope: !725, file: !725, line: 289, type: !1382, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1386 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !1387, file: !1393, line: 247)
!1387 = !DIDerivedType(tag: DW_TAG_typedef, name: "imaxdiv_t", file: !1388, line: 242, baseType: !1389)
!1388 = !DIFile(filename: "/Applications/Xcode_16.app/Contents/Developer/Platforms/MacOSX.platform/Developer/SDKs/MacOSX15.0.sdk/usr/include/_inttypes.h", directory: "")
!1389 = distinct !DICompositeType(tag: DW_TAG_structure_type, file: !1388, line: 239, size: 128, flags: DIFlagTypePassByValue, elements: !1390, identifier: "_ZTS9imaxdiv_t")
!1390 = !{!1391, !1392}
!1391 = !DIDerivedType(tag: DW_TAG_member, name: "quot", scope: !1389, file: !1388, line: 240, baseType: !479, size: 64)
!1392 = !DIDerivedType(tag: DW_TAG_member, name: "rem", scope: !1389, file: !1388, line: 241, baseType: !479, size: 64, offset: 64)
!1393 = !DIFile(filename: ".konan/dependencies/apple-llvm-20200714-macos-aarch64-1/bin/../include/c++/v1/cinttypes", directory: "/Users/bytedance")
!1394 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !1395, file: !1393, line: 248)
!1395 = !DISubprogram(name: "imaxabs", scope: !1388, file: !1388, line: 236, type: !1396, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1396 = !DISubroutineType(types: !1397)
!1397 = !{!479, !479}
!1398 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !1399, file: !1393, line: 249)
!1399 = !DISubprogram(name: "imaxdiv", scope: !1388, file: !1388, line: 246, type: !1400, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1400 = !DISubroutineType(types: !1401)
!1401 = !{!1387, !479, !479}
!1402 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !1403, file: !1393, line: 250)
!1403 = !DISubprogram(name: "strtoimax", scope: !1388, file: !1388, line: 251, type: !1404, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1404 = !DISubroutineType(types: !1405)
!1405 = !{!479, !41, !526, !29}
!1406 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !1407, file: !1393, line: 251)
!1407 = !DISubprogram(name: "strtoumax", scope: !1388, file: !1388, line: 257, type: !1408, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1408 = !DISubroutineType(types: !1409)
!1409 = !{!482, !41, !526, !29}
!1410 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !1411, file: !1393, line: 252)
!1411 = !DISubprogram(name: "wcstoimax", scope: !1388, file: !1388, line: 264, type: !1412, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1412 = !DISubroutineType(types: !1413)
!1413 = !{!479, !651, !1414, !29}
!1414 = !DIDerivedType(tag: DW_TAG_pointer_type, baseType: !637, size: 64)
!1415 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !1416, file: !1393, line: 253)
!1416 = !DISubprogram(name: "wcstoumax", scope: !1388, file: !1388, line: 270, type: !1417, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1417 = !DISubroutineType(types: !1418)
!1418 = !{!482, !651, !1414, !29}
!1419 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !1420, file: !1422, line: 103)
!1420 = !DISubprogram(name: "isalnum", linkageName: "_Z7isalnumi", scope: !1421, file: !1421, line: 214, type: !931, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1421 = !DIFile(filename: "/Applications/Xcode_16.app/Contents/Developer/Platforms/MacOSX.platform/Developer/SDKs/MacOSX15.0.sdk/usr/include/_ctype.h", directory: "")
!1422 = !DIFile(filename: ".konan/dependencies/apple-llvm-20200714-macos-aarch64-1/bin/../include/c++/v1/cctype", directory: "/Users/bytedance")
!1423 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !1424, file: !1422, line: 104)
!1424 = !DISubprogram(name: "isalpha", linkageName: "_Z7isalphai", scope: !1421, file: !1421, line: 220, type: !931, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1425 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !1426, file: !1422, line: 105)
!1426 = !DISubprogram(name: "isblank", linkageName: "_Z7isblanki", scope: !1421, file: !1421, line: 226, type: !931, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1427 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !1428, file: !1422, line: 106)
!1428 = !DISubprogram(name: "iscntrl", linkageName: "_Z7iscntrli", scope: !1421, file: !1421, line: 232, type: !931, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1429 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !1430, file: !1422, line: 107)
!1430 = !DISubprogram(name: "isdigit", linkageName: "_Z7isdigiti", scope: !1421, file: !1421, line: 239, type: !931, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1431 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !1432, file: !1422, line: 108)
!1432 = !DISubprogram(name: "isgraph", linkageName: "_Z7isgraphi", scope: !1421, file: !1421, line: 245, type: !931, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1433 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !1434, file: !1422, line: 109)
!1434 = !DISubprogram(name: "islower", linkageName: "_Z7isloweri", scope: !1421, file: !1421, line: 251, type: !931, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1435 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !1436, file: !1422, line: 110)
!1436 = !DISubprogram(name: "isprint", linkageName: "_Z7isprinti", scope: !1421, file: !1421, line: 257, type: !931, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1437 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !1438, file: !1422, line: 111)
!1438 = !DISubprogram(name: "ispunct", linkageName: "_Z7ispuncti", scope: !1421, file: !1421, line: 263, type: !931, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1439 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !1440, file: !1422, line: 112)
!1440 = !DISubprogram(name: "isspace", linkageName: "_Z7isspacei", scope: !1421, file: !1421, line: 269, type: !931, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1441 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !1442, file: !1422, line: 113)
!1442 = !DISubprogram(name: "isupper", linkageName: "_Z7isupperi", scope: !1421, file: !1421, line: 275, type: !931, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1443 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !1444, file: !1422, line: 114)
!1444 = !DISubprogram(name: "isxdigit", linkageName: "_Z8isxdigiti", scope: !1421, file: !1421, line: 282, type: !931, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1445 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !1446, file: !1422, line: 115)
!1446 = !DISubprogram(name: "tolower", linkageName: "_Z7toloweri", scope: !1421, file: !1421, line: 294, type: !931, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1447 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !1448, file: !1422, line: 116)
!1448 = !DISubprogram(name: "toupper", linkageName: "_Z7toupperi", scope: !1421, file: !1421, line: 300, type: !931, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1449 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !1450, file: !1453, line: 62)
!1450 = !DIDerivedType(tag: DW_TAG_typedef, name: "wint_t", file: !1451, line: 32, baseType: !1452)
!1451 = !DIFile(filename: "/Applications/Xcode_16.app/Contents/Developer/Platforms/MacOSX.platform/Developer/SDKs/MacOSX15.0.sdk/usr/include/sys/_types/_wint_t.h", directory: "")
!1452 = !DIDerivedType(tag: DW_TAG_typedef, name: "__darwin_wint_t", file: !331, line: 111, baseType: !29)
!1453 = !DIFile(filename: ".konan/dependencies/apple-llvm-20200714-macos-aarch64-1/bin/../include/c++/v1/cwctype", directory: "/Users/bytedance")
!1454 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !1455, file: !1453, line: 63)
!1455 = !DIDerivedType(tag: DW_TAG_typedef, name: "wctrans_t", file: !1456, line: 32, baseType: !1457)
!1456 = !DIFile(filename: "/Applications/Xcode_16.app/Contents/Developer/Platforms/MacOSX.platform/Developer/SDKs/MacOSX15.0.sdk/usr/include/_types/_wctrans_t.h", directory: "")
!1457 = !DIDerivedType(tag: DW_TAG_typedef, name: "__darwin_wctrans_t", file: !1458, line: 41, baseType: !29)
!1458 = !DIFile(filename: "/Applications/Xcode_16.app/Contents/Developer/Platforms/MacOSX.platform/Developer/SDKs/MacOSX15.0.sdk/usr/include/_types.h", directory: "")
!1459 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !1460, file: !1453, line: 64)
!1460 = !DIDerivedType(tag: DW_TAG_typedef, name: "wctype_t", file: !1461, line: 32, baseType: !1462)
!1461 = !DIFile(filename: "/Applications/Xcode_16.app/Contents/Developer/Platforms/MacOSX.platform/Developer/SDKs/MacOSX15.0.sdk/usr/include/_types/_wctype_t.h", directory: "")
!1462 = !DIDerivedType(tag: DW_TAG_typedef, name: "__darwin_wctype_t", file: !1458, line: 43, baseType: !1463)
!1463 = !DIDerivedType(tag: DW_TAG_typedef, name: "__uint32_t", file: !331, line: 36, baseType: !50)
!1464 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !1465, file: !1453, line: 65)
!1465 = !DISubprogram(name: "iswalnum", linkageName: "_Z8iswalnumi", scope: !1466, file: !1466, line: 81, type: !1467, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1466 = !DIFile(filename: "/Applications/Xcode_16.app/Contents/Developer/Platforms/MacOSX.platform/Developer/SDKs/MacOSX15.0.sdk/usr/include/___wctype.h", directory: "")
!1467 = !DISubroutineType(types: !1468)
!1468 = !{!29, !1450}
!1469 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !1470, file: !1453, line: 66)
!1470 = !DISubprogram(name: "iswalpha", linkageName: "_Z8iswalphai", scope: !1466, file: !1466, line: 87, type: !1467, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1471 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !1472, file: !1453, line: 67)
!1472 = !DISubprogram(name: "iswblank", linkageName: "_Z8iswblanki", scope: !1473, file: !1473, line: 50, type: !1467, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1473 = !DIFile(filename: "/Applications/Xcode_16.app/Contents/Developer/Platforms/MacOSX.platform/Developer/SDKs/MacOSX15.0.sdk/usr/include/_wctype.h", directory: "")
!1474 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !1475, file: !1453, line: 68)
!1475 = !DISubprogram(name: "iswcntrl", linkageName: "_Z8iswcntrli", scope: !1466, file: !1466, line: 93, type: !1467, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1476 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !1477, file: !1453, line: 69)
!1477 = !DISubprogram(name: "iswdigit", linkageName: "_Z8iswdigiti", scope: !1466, file: !1466, line: 105, type: !1467, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1478 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !1479, file: !1453, line: 70)
!1479 = !DISubprogram(name: "iswgraph", linkageName: "_Z8iswgraphi", scope: !1466, file: !1466, line: 111, type: !1467, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1480 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !1481, file: !1453, line: 71)
!1481 = !DISubprogram(name: "iswlower", linkageName: "_Z8iswloweri", scope: !1466, file: !1466, line: 117, type: !1467, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1482 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !1483, file: !1453, line: 72)
!1483 = !DISubprogram(name: "iswprint", linkageName: "_Z8iswprinti", scope: !1466, file: !1466, line: 123, type: !1467, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1484 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !1485, file: !1453, line: 73)
!1485 = !DISubprogram(name: "iswpunct", linkageName: "_Z8iswpuncti", scope: !1466, file: !1466, line: 129, type: !1467, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1486 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !1487, file: !1453, line: 74)
!1487 = !DISubprogram(name: "iswspace", linkageName: "_Z8iswspacei", scope: !1466, file: !1466, line: 135, type: !1467, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1488 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !1489, file: !1453, line: 75)
!1489 = !DISubprogram(name: "iswupper", linkageName: "_Z8iswupperi", scope: !1466, file: !1466, line: 141, type: !1467, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1490 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !1491, file: !1453, line: 76)
!1491 = !DISubprogram(name: "iswxdigit", linkageName: "_Z9iswxdigiti", scope: !1466, file: !1466, line: 147, type: !1467, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1492 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !1493, file: !1453, line: 77)
!1493 = !DISubprogram(name: "iswctype", linkageName: "_Z8iswctypeij", scope: !1466, file: !1466, line: 99, type: !1494, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1494 = !DISubroutineType(types: !1495)
!1495 = !{!29, !1450, !1460}
!1496 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !1497, file: !1453, line: 78)
!1497 = !DISubprogram(name: "wctype", scope: !1466, file: !1466, line: 187, type: !1498, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1498 = !DISubroutineType(types: !1499)
!1499 = !{!1460, !41}
!1500 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !1501, file: !1453, line: 79)
!1501 = !DISubprogram(name: "towlower", linkageName: "_Z8towloweri", scope: !1466, file: !1466, line: 153, type: !1502, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1502 = !DISubroutineType(types: !1503)
!1503 = !{!1450, !1450}
!1504 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !1505, file: !1453, line: 80)
!1505 = !DISubprogram(name: "towupper", linkageName: "_Z8towupperi", scope: !1466, file: !1466, line: 159, type: !1502, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1506 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !1507, file: !1453, line: 81)
!1507 = !DISubprogram(name: "towctrans", scope: !1473, file: !1473, line: 121, type: !1508, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1508 = !DISubroutineType(types: !1509)
!1509 = !{!1450, !1450, !1455}
!1510 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !1511, file: !1453, line: 82)
!1511 = !DISubprogram(name: "wctrans", scope: !1473, file: !1473, line: 123, type: !1512, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1512 = !DISubroutineType(types: !1513)
!1513 = !{!1455, !41}
!1514 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !1515, file: !1526, line: 115)
!1515 = !DIDerivedType(tag: DW_TAG_typedef, name: "mbstate_t", file: !1516, line: 32, baseType: !1517)
!1516 = !DIFile(filename: "/Applications/Xcode_16.app/Contents/Developer/Platforms/MacOSX.platform/Developer/SDKs/MacOSX15.0.sdk/usr/include/sys/_types/_mbstate_t.h", directory: "")
!1517 = !DIDerivedType(tag: DW_TAG_typedef, name: "__darwin_mbstate_t", file: !331, line: 72, baseType: !1518)
!1518 = !DIDerivedType(tag: DW_TAG_typedef, name: "__mbstate_t", file: !331, line: 70, baseType: !1519)
!1519 = distinct !DICompositeType(tag: DW_TAG_union_type, file: !331, line: 67, size: 1024, flags: DIFlagTypePassByValue, elements: !1520, identifier: "_ZTS11__mbstate_t")
!1520 = !{!1521, !1525}
!1521 = !DIDerivedType(tag: DW_TAG_member, name: "__mbstate8", scope: !1519, file: !331, line: 68, baseType: !1522, size: 1024)
!1522 = !DICompositeType(tag: DW_TAG_array_type, baseType: !43, size: 1024, elements: !1523)
!1523 = !{!1524}
!1524 = !DISubrange(count: 128)
!1525 = !DIDerivedType(tag: DW_TAG_member, name: "_mbstateL", scope: !1519, file: !331, line: 69, baseType: !427, size: 64)
!1526 = !DIFile(filename: ".konan/dependencies/apple-llvm-20200714-macos-aarch64-1/bin/../include/c++/v1/cwchar", directory: "/Users/bytedance")
!1527 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !1255, file: !1526, line: 116)
!1528 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !664, file: !1526, line: 117)
!1529 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !1450, file: !1526, line: 118)
!1530 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !724, file: !1526, line: 119)
!1531 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !1532, file: !1526, line: 120)
!1532 = !DISubprogram(name: "fwprintf", scope: !1533, file: !1533, line: 103, type: !1534, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1533 = !DIFile(filename: "/Applications/Xcode_16.app/Contents/Developer/Platforms/MacOSX.platform/Developer/SDKs/MacOSX15.0.sdk/usr/include/_wchar.h", directory: "")
!1534 = !DISubroutineType(types: !1535)
!1535 = !{!29, !784, !651, null}
!1536 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !1537, file: !1526, line: 121)
!1537 = !DISubprogram(name: "fwscanf", scope: !1533, file: !1533, line: 104, type: !1534, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1538 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !1539, file: !1526, line: 122)
!1539 = !DISubprogram(name: "swprintf", scope: !1533, file: !1533, line: 115, type: !1540, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1540 = !DISubroutineType(types: !1541)
!1541 = !{!29, !637, !1255, !651, null}
!1542 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !1543, file: !1526, line: 123)
!1543 = !DISubprogram(name: "vfwprintf", scope: !1533, file: !1533, line: 118, type: !1544, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1544 = !DISubroutineType(types: !1545)
!1545 = !{!29, !784, !651, !1356}
!1546 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !1547, file: !1526, line: 124)
!1547 = !DISubprogram(name: "vswprintf", scope: !1533, file: !1533, line: 120, type: !1548, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1548 = !DISubroutineType(types: !1549)
!1549 = !{!29, !637, !1255, !651, !1356}
!1550 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !1551, file: !1526, line: 125)
!1551 = !DISubprogram(name: "swscanf", scope: !1533, file: !1533, line: 116, type: !1552, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1552 = !DISubroutineType(types: !1553)
!1553 = !{!29, !651, !651, null}
!1554 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !1555, file: !1526, line: 126)
!1555 = !DISubprogram(name: "vfwscanf", scope: !1533, file: !1533, line: 170, type: !1544, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1556 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !1557, file: !1526, line: 127)
!1557 = !DISubprogram(name: "vswscanf", scope: !1533, file: !1533, line: 172, type: !1558, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1558 = !DISubroutineType(types: !1559)
!1559 = !{!29, !651, !651, !1356}
!1560 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !1561, file: !1526, line: 128)
!1561 = !DISubprogram(name: "fgetwc", scope: !1533, file: !1533, line: 98, type: !1562, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1562 = !DISubroutineType(types: !1563)
!1563 = !{!1450, !784}
!1564 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !1565, file: !1526, line: 129)
!1565 = !DISubprogram(name: "fgetws", scope: !1533, file: !1533, line: 99, type: !1566, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1566 = !DISubroutineType(types: !1567)
!1567 = !{!637, !637, !29, !784}
!1568 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !1569, file: !1526, line: 130)
!1569 = !DISubprogram(name: "fputwc", scope: !1533, file: !1533, line: 100, type: !1570, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1570 = !DISubroutineType(types: !1571)
!1571 = !{!1450, !638, !784}
!1572 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !1573, file: !1526, line: 131)
!1573 = !DISubprogram(name: "fputws", scope: !1533, file: !1533, line: 101, type: !1574, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1574 = !DISubroutineType(types: !1575)
!1575 = !{!29, !651, !784}
!1576 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !1577, file: !1526, line: 132)
!1577 = !DISubprogram(name: "fwide", scope: !1533, file: !1533, line: 102, type: !1578, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1578 = !DISubroutineType(types: !1579)
!1579 = !{!29, !784, !29}
!1580 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !1581, file: !1526, line: 133)
!1581 = !DISubprogram(name: "getwc", scope: !1533, file: !1533, line: 105, type: !1562, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1582 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !1583, file: !1526, line: 134)
!1583 = !DISubprogram(name: "putwc", scope: !1533, file: !1533, line: 113, type: !1570, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1584 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !1585, file: !1526, line: 135)
!1585 = !DISubprogram(name: "ungetwc", scope: !1533, file: !1533, line: 117, type: !1586, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1586 = !DISubroutineType(types: !1587)
!1587 = !{!1450, !1450, !784}
!1588 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !1589, file: !1526, line: 136)
!1589 = !DISubprogram(name: "wcstod", scope: !1533, file: !1533, line: 144, type: !1590, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1590 = !DISubroutineType(types: !1591)
!1591 = !{!509, !651, !1414}
!1592 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !1593, file: !1526, line: 137)
!1593 = !DISubprogram(name: "wcstof", scope: !1533, file: !1533, line: 175, type: !1594, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1594 = !DISubroutineType(types: !1595)
!1595 = !{!531, !651, !1414}
!1596 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !1597, file: !1526, line: 138)
!1597 = !DISubprogram(name: "wcstold", scope: !1533, file: !1533, line: 177, type: !1598, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1598 = !DISubroutineType(types: !1599)
!1599 = !{!335, !651, !1414}
!1600 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !1601, file: !1526, line: 139)
!1601 = !DISubprogram(name: "wcstol", scope: !1533, file: !1533, line: 147, type: !1602, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1602 = !DISubroutineType(types: !1603)
!1603 = !{!325, !651, !1414, !29}
!1604 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !1605, file: !1526, line: 141)
!1605 = !DISubprogram(name: "wcstoll", scope: !1533, file: !1533, line: 180, type: !1606, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1606 = !DISubroutineType(types: !1607)
!1607 = !{!427, !651, !1414, !29}
!1608 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !1609, file: !1526, line: 143)
!1609 = !DISubprogram(name: "wcstoul", scope: !1533, file: !1533, line: 149, type: !1610, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1610 = !DISubroutineType(types: !1611)
!1611 = !{!305, !651, !1414, !29}
!1612 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !1613, file: !1526, line: 145)
!1613 = !DISubprogram(name: "wcstoull", scope: !1533, file: !1533, line: 182, type: !1614, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1614 = !DISubroutineType(types: !1615)
!1615 = !{!437, !651, !1414, !29}
!1616 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !1617, file: !1526, line: 147)
!1617 = !DISubprogram(name: "wcscpy", scope: !1533, file: !1533, line: 128, type: !1618, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1618 = !DISubroutineType(types: !1619)
!1619 = !{!637, !637, !651}
!1620 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !1621, file: !1526, line: 148)
!1621 = !DISubprogram(name: "wcsncpy", scope: !1533, file: !1533, line: 135, type: !1622, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1622 = !DISubroutineType(types: !1623)
!1623 = !{!637, !637, !651, !1255}
!1624 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !1625, file: !1526, line: 149)
!1625 = !DISubprogram(name: "wcscat", scope: !1533, file: !1533, line: 124, type: !1618, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1626 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !1627, file: !1526, line: 150)
!1627 = !DISubprogram(name: "wcsncat", scope: !1533, file: !1533, line: 133, type: !1622, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1628 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !1629, file: !1526, line: 151)
!1629 = !DISubprogram(name: "wcscmp", scope: !1533, file: !1533, line: 126, type: !1630, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1630 = !DISubroutineType(types: !1631)
!1631 = !{!29, !651, !651}
!1632 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !1633, file: !1526, line: 152)
!1633 = !DISubprogram(name: "wcscoll", scope: !1533, file: !1533, line: 127, type: !1630, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1634 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !1635, file: !1526, line: 153)
!1635 = !DISubprogram(name: "wcsncmp", scope: !1533, file: !1533, line: 134, type: !1636, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1636 = !DISubroutineType(types: !1637)
!1637 = !{!29, !651, !651, !1255}
!1638 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !1639, file: !1526, line: 154)
!1639 = !DISubprogram(name: "wcsxfrm", scope: !1533, file: !1533, line: 142, type: !1640, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1640 = !DISubroutineType(types: !1641)
!1641 = !{!1255, !637, !651, !1255}
!1642 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !1643, file: !1526, line: 155)
!1643 = !DISubprogram(name: "wcschr", linkageName: "_Z6wcschrUa9enable_ifIXLb1EEEPww", scope: !1644, file: !1644, line: 141, type: !1645, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1644 = !DIFile(filename: ".konan/dependencies/apple-llvm-20200714-macos-aarch64-1/bin/../include/c++/v1/wchar.h", directory: "/Users/bytedance")
!1645 = !DISubroutineType(types: !1646)
!1646 = !{!637, !637, !638}
!1647 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !1648, file: !1526, line: 156)
!1648 = !DISubprogram(name: "wcspbrk", linkageName: "_Z7wcspbrkUa9enable_ifIXLb1EEEPwPKw", scope: !1644, file: !1644, line: 148, type: !1618, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1649 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !1650, file: !1526, line: 157)
!1650 = !DISubprogram(name: "wcsrchr", linkageName: "_Z7wcsrchrUa9enable_ifIXLb1EEEPww", scope: !1644, file: !1644, line: 155, type: !1645, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1651 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !1652, file: !1526, line: 158)
!1652 = !DISubprogram(name: "wcsstr", linkageName: "_Z6wcsstrUa9enable_ifIXLb1EEEPwPKw", scope: !1644, file: !1644, line: 162, type: !1618, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1653 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !1654, file: !1526, line: 159)
!1654 = !DISubprogram(name: "wmemchr", linkageName: "_Z7wmemchrUa9enable_ifIXLb1EEEPwwm", scope: !1644, file: !1644, line: 169, type: !1655, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1655 = !DISubroutineType(types: !1656)
!1656 = !{!637, !637, !638, !1255}
!1657 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !1658, file: !1526, line: 160)
!1658 = !DISubprogram(name: "wcscspn", scope: !1533, file: !1533, line: 129, type: !1659, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1659 = !DISubroutineType(types: !1660)
!1660 = !{!1255, !651, !651}
!1661 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !1662, file: !1526, line: 161)
!1662 = !DISubprogram(name: "wcslen", scope: !1533, file: !1533, line: 132, type: !1663, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1663 = !DISubroutineType(types: !1664)
!1664 = !{!1255, !651}
!1665 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !1666, file: !1526, line: 162)
!1666 = !DISubprogram(name: "wcsspn", scope: !1533, file: !1533, line: 140, type: !1659, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1667 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !1668, file: !1526, line: 163)
!1668 = !DISubprogram(name: "wcstok", scope: !1533, file: !1533, line: 145, type: !1669, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1669 = !DISubroutineType(types: !1670)
!1670 = !{!637, !637, !651, !1414}
!1671 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !1672, file: !1526, line: 164)
!1672 = !DISubprogram(name: "wmemcmp", scope: !1533, file: !1533, line: 151, type: !1636, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1673 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !1674, file: !1526, line: 165)
!1674 = !DISubprogram(name: "wmemcpy", scope: !1533, file: !1533, line: 152, type: !1622, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1675 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !1676, file: !1526, line: 166)
!1676 = !DISubprogram(name: "wmemmove", scope: !1533, file: !1533, line: 153, type: !1622, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1677 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !1678, file: !1526, line: 167)
!1678 = !DISubprogram(name: "wmemset", scope: !1533, file: !1533, line: 154, type: !1655, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1679 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !1680, file: !1526, line: 168)
!1680 = !DISubprogram(name: "wcsftime", linkageName: "\01_wcsftime", scope: !1533, file: !1533, line: 130, type: !1681, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1681 = !DISubroutineType(types: !1682)
!1682 = !{!1255, !637, !1255, !651, !700}
!1683 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !1684, file: !1526, line: 169)
!1684 = !DISubprogram(name: "btowc", scope: !1533, file: !1533, line: 97, type: !1685, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1685 = !DISubroutineType(types: !1686)
!1686 = !{!1450, !29}
!1687 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !1688, file: !1526, line: 170)
!1688 = !DISubprogram(name: "wctob", scope: !1533, file: !1533, line: 143, type: !1467, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1689 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !1690, file: !1526, line: 171)
!1690 = !DISubprogram(name: "mbsinit", scope: !1533, file: !1533, line: 110, type: !1691, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1691 = !DISubroutineType(types: !1692)
!1692 = !{!29, !1693}
!1693 = !DIDerivedType(tag: DW_TAG_pointer_type, baseType: !1694, size: 64)
!1694 = !DIDerivedType(tag: DW_TAG_const_type, baseType: !1515)
!1695 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !1696, file: !1526, line: 172)
!1696 = !DISubprogram(name: "mbrlen", scope: !1533, file: !1533, line: 107, type: !1697, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1697 = !DISubroutineType(types: !1698)
!1698 = !{!1255, !41, !1255, !1699}
!1699 = !DIDerivedType(tag: DW_TAG_pointer_type, baseType: !1515, size: 64)
!1700 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !1701, file: !1526, line: 173)
!1701 = !DISubprogram(name: "mbrtowc", scope: !1533, file: !1533, line: 108, type: !1702, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1702 = !DISubroutineType(types: !1703)
!1703 = !{!1255, !637, !41, !1255, !1699}
!1704 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !1705, file: !1526, line: 174)
!1705 = !DISubprogram(name: "wcrtomb", scope: !1533, file: !1533, line: 123, type: !1706, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1706 = !DISubroutineType(types: !1707)
!1707 = !{!1255, !354, !638, !1699}
!1708 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !1709, file: !1526, line: 175)
!1709 = !DISubprogram(name: "mbsrtowcs", scope: !1533, file: !1533, line: 111, type: !1710, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1710 = !DISubroutineType(types: !1711)
!1711 = !{!1255, !637, !40, !1255, !1699}
!1712 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !1713, file: !1526, line: 176)
!1713 = !DISubprogram(name: "wcsrtombs", scope: !1533, file: !1533, line: 138, type: !1714, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1714 = !DISubroutineType(types: !1715)
!1715 = !{!1255, !354, !1716, !1255, !1699}
!1716 = !DIDerivedType(tag: DW_TAG_pointer_type, baseType: !651, size: 64)
!1717 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !1718, file: !1526, line: 179)
!1718 = !DISubprogram(name: "getwchar", scope: !1533, file: !1533, line: 106, type: !1719, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1719 = !DISubroutineType(types: !1720)
!1720 = !{!1450}
!1721 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !1722, file: !1526, line: 180)
!1722 = !DISubprogram(name: "vwscanf", scope: !1533, file: !1533, line: 174, type: !1723, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1723 = !DISubroutineType(types: !1724)
!1724 = !{!29, !651, !1356}
!1725 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !1726, file: !1526, line: 181)
!1726 = !DISubprogram(name: "wscanf", scope: !1533, file: !1533, line: 156, type: !1727, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1727 = !DISubroutineType(types: !1728)
!1728 = !{!29, !651, null}
!1729 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !1730, file: !1526, line: 185)
!1730 = !DISubprogram(name: "putwchar", scope: !1533, file: !1533, line: 114, type: !1731, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1731 = !DISubroutineType(types: !1732)
!1732 = !{!1450, !638}
!1733 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !1734, file: !1526, line: 186)
!1734 = !DISubprogram(name: "vwprintf", scope: !1533, file: !1533, line: 122, type: !1723, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1735 = !DIImportedEntity(tag: DW_TAG_imported_declaration, scope: !321, entity: !1736, file: !1526, line: 187)
!1736 = !DISubprogram(name: "wprintf", scope: !1533, file: !1533, line: 155, type: !1727, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1737 = distinct !DICompositeType(tag: DW_TAG_structure_type, name: "InitNode", file: !1738, line: 17, size: 128, flags: DIFlagTypePassByValue, elements: !1739, identifier: "_ZTS8InitNode")
!1738 = !DIFile(filename: "main/cpp/Runtime.h", directory: "/Users/bytedance/KMP/kt2/kotlin_2.0/kotlin-native/runtime/src")
!1739 = !{!1740, !1747}
!1740 = !DIDerivedType(tag: DW_TAG_member, name: "init", scope: !1737, file: !1738, line: 18, baseType: !1741, size: 64)
!1741 = !DIDerivedType(tag: DW_TAG_typedef, name: "Initializer", file: !1738, line: 16, baseType: !1742)
!1742 = !DIDerivedType(tag: DW_TAG_pointer_type, baseType: !1743, size: 64)
!1743 = !DISubroutineType(types: !1744)
!1744 = !{null, !29, !1745}
!1745 = !DIDerivedType(tag: DW_TAG_pointer_type, baseType: !1746, size: 64)
!1746 = distinct !DICompositeType(tag: DW_TAG_structure_type, name: "MemoryState", file: !77, line: 184, flags: DIFlagFwdDecl | DIFlagNonTrivial, identifier: "_ZTS11MemoryState")
!1747 = !DIDerivedType(tag: DW_TAG_member, name: "next", scope: !1737, file: !1738, line: 19, baseType: !1748, size: 64, offset: 64)
!1748 = !DIDerivedType(tag: DW_TAG_pointer_type, baseType: !1737, size: 64)
!1749 = !{!"clang version 11.1.0 (https://github.com/apple/llvm-project 9205ffc7869a87cf3906b80dbd45b969c5794ef7)"}
!1750 = !{i32 2, !"SDK Version", [2 x i32] [i32 15, i32 0]}
!1751 = !{i32 1, !"Objective-C Version", i32 2}
!1752 = !{i32 1, !"Objective-C Image Info Version", i32 0}
!1753 = !{i32 1, !"Objective-C Image Info Section", !"__DATA,__objc_imageinfo,regular,no_dead_strip"}
!1754 = !{i32 1, !"Objective-C Garbage Collection", i8 0}
!1755 = !{i32 1, !"Objective-C Class Properties", i32 64}
!1756 = !{i32 7, !"Dwarf Version", i32 2}
!1757 = !{i32 2, !"Debug Info Version", i32 3}
!1758 = !{i32 1, !"wchar_size", i32 4}
!1759 = !{i32 7, !"PIC Level", i32 2}
!1760 = distinct !DISubprogram(name: "touchNSString", scope: !10, file: !10, line: 32, type: !1761, scopeLine: 32, flags: DIFlagPrototyped, spFlags: DISPFlagDefinition | DISPFlagOptimized, unit: !2, retainedNodes: !4)
!1761 = !DISubroutineType(types: !1762)
!1762 = !{!1763}
!1763 = !DIDerivedType(tag: DW_TAG_pointer_type, baseType: !1764, size: 64)
!1764 = !DICompositeType(tag: DW_TAG_structure_type, name: "NSString", scope: !10, file: !1765, line: 103, size: 64, elements: !1766, runtimeLang: DW_LANG_ObjC_plus_plus)
!1765 = !DIFile(filename: "/Applications/Xcode_16.app/Contents/Developer/Platforms/MacOSX.platform/Developer/SDKs/MacOSX15.0.sdk/System/Library/Frameworks/Foundation.framework/Headers/NSString.h", directory: "")
!1766 = !{!1767, !1772}
!1767 = !DIDerivedType(tag: DW_TAG_inheritance, scope: !1764, baseType: !1768, extraData: i32 0)
!1768 = !DICompositeType(tag: DW_TAG_structure_type, name: "NSObject", scope: !10, file: !1769, line: 53, size: 64, elements: !1770, runtimeLang: DW_LANG_ObjC_plus_plus)
!1769 = !DIFile(filename: "/Applications/Xcode_16.app/Contents/Developer/Platforms/MacOSX.platform/Developer/SDKs/MacOSX15.0.sdk/usr/include/objc/NSObject.h", directory: "")
!1770 = !{!1771}
!1771 = !DIDerivedType(tag: DW_TAG_member, name: "isa", scope: !1769, file: !1769, line: 56, baseType: !168, size: 64, flags: DIFlagProtected)
!1772 = !DIObjCProperty(name: "length", file: !1765, line: 109, attributes: 257, type: !1773)
!1773 = !DIDerivedType(tag: DW_TAG_typedef, name: "NSUInteger", file: !1774, line: 14, baseType: !305)
!1774 = !DIFile(filename: "/Applications/Xcode_16.app/Contents/Developer/Platforms/MacOSX.platform/Developer/SDKs/MacOSX15.0.sdk/usr/include/objc/NSObjCRuntime.h", directory: "")
!1775 = !DILocation(line: 32, column: 36, scope: !1760)
!1776 = !DILocation(line: 32, column: 29, scope: !1760)
!1777 = distinct !DISubprogram(name: "touchCreateKotlinObjCClass", scope: !10, file: !10, line: 34, type: !1778, scopeLine: 34, flags: DIFlagPrototyped, spFlags: DISPFlagDefinition | DISPFlagOptimized, unit: !2, retainedNodes: !4)
!1778 = !DISubroutineType(types: !5)
!1779 = !DILocation(line: 34, column: 1, scope: !1777)
!1780 = distinct !DISubprogram(name: "touchGetObjCKotlinTypeInfo", scope: !10, file: !10, line: 35, type: !1778, scopeLine: 35, flags: DIFlagPrototyped, spFlags: DISPFlagDefinition | DISPFlagOptimized, unit: !2, retainedNodes: !4)
!1781 = !DILocation(line: 35, column: 1, scope: !1780)
!1782 = distinct !DISubprogram(name: "touchMissingInitImp", scope: !10, file: !10, line: 36, type: !1778, scopeLine: 36, flags: DIFlagPrototyped, spFlags: DISPFlagDefinition | DISPFlagOptimized, unit: !2, retainedNodes: !4)
!1783 = !DILocation(line: 36, column: 1, scope: !1782)
!1784 = distinct !DISubprogram(name: "touchKotlin_Interop_DoesObjectConformToProtocol", scope: !10, file: !10, line: 38, type: !1778, scopeLine: 38, flags: DIFlagPrototyped, spFlags: DISPFlagDefinition | DISPFlagOptimized, unit: !2, retainedNodes: !4)
!1785 = !DILocation(line: 38, column: 1, scope: !1784)
!1786 = distinct !DISubprogram(name: "touchKotlin_Interop_IsObjectKindOfClass", scope: !10, file: !10, line: 39, type: !1778, scopeLine: 39, flags: DIFlagPrototyped, spFlags: DISPFlagDefinition | DISPFlagOptimized, unit: !2, retainedNodes: !4)
!1787 = !DILocation(line: 39, column: 1, scope: !1786)
!1788 = distinct !DISubprogram(name: "touchKotlin_ObjCExport_refToLocalObjC", scope: !10, file: !10, line: 41, type: !1778, scopeLine: 41, flags: DIFlagPrototyped, spFlags: DISPFlagDefinition | DISPFlagOptimized, unit: !2, retainedNodes: !4)
!1789 = !DILocation(line: 41, column: 1, scope: !1788)
!1790 = distinct !DISubprogram(name: "touchKotlin_ObjCExport_refToRetainedObjC", scope: !10, file: !10, line: 42, type: !1778, scopeLine: 42, flags: DIFlagPrototyped, spFlags: DISPFlagDefinition | DISPFlagOptimized, unit: !2, retainedNodes: !4)
!1791 = !DILocation(line: 42, column: 1, scope: !1790)
!1792 = distinct !DISubprogram(name: "touchKotlin_ObjCExport_refFromObjC", scope: !10, file: !10, line: 43, type: !1778, scopeLine: 43, flags: DIFlagPrototyped, spFlags: DISPFlagDefinition | DISPFlagOptimized, unit: !2, retainedNodes: !4)
!1793 = !DILocation(line: 43, column: 1, scope: !1792)
!1794 = distinct !DISubprogram(name: "touchKotlin_ObjCExport_CreateRetainedNSStringFromKString", scope: !10, file: !10, line: 44, type: !1778, scopeLine: 44, flags: DIFlagPrototyped, spFlags: DISPFlagDefinition | DISPFlagOptimized, unit: !2, retainedNodes: !4)
!1795 = !DILocation(line: 44, column: 1, scope: !1794)
!1796 = distinct !DISubprogram(name: "touchKotlin_ObjCExport_convertUnitToRetained", scope: !10, file: !10, line: 45, type: !1778, scopeLine: 45, flags: DIFlagPrototyped, spFlags: DISPFlagDefinition | DISPFlagOptimized, unit: !2, retainedNodes: !4)
!1797 = !DILocation(line: 45, column: 1, scope: !1796)
!1798 = distinct !DISubprogram(name: "touchKotlin_ObjCExport_GetAssociatedObject", scope: !10, file: !10, line: 46, type: !1778, scopeLine: 46, flags: DIFlagPrototyped, spFlags: DISPFlagDefinition | DISPFlagOptimized, unit: !2, retainedNodes: !4)
!1799 = !DILocation(line: 46, column: 1, scope: !1798)
!1800 = distinct !DISubprogram(name: "touchKotlin_ObjCExport_AbstractMethodCalled", scope: !10, file: !10, line: 47, type: !1778, scopeLine: 47, flags: DIFlagPrototyped, spFlags: DISPFlagDefinition | DISPFlagOptimized, unit: !2, retainedNodes: !4)
!1801 = !DILocation(line: 47, column: 1, scope: !1800)
!1802 = distinct !DISubprogram(name: "touchKotlin_ObjCExport_AbstractClassConstructorCalled", scope: !10, file: !10, line: 48, type: !1778, scopeLine: 48, flags: DIFlagPrototyped, spFlags: DISPFlagDefinition | DISPFlagOptimized, unit: !2, retainedNodes: !4)
!1803 = !DILocation(line: 48, column: 1, scope: !1802)
!1804 = distinct !DISubprogram(name: "touchKotlin_ObjCExport_RethrowExceptionAsNSError", scope: !10, file: !10, line: 49, type: !1778, scopeLine: 49, flags: DIFlagPrototyped, spFlags: DISPFlagDefinition | DISPFlagOptimized, unit: !2, retainedNodes: !4)
!1805 = !DILocation(line: 49, column: 1, scope: !1804)
!1806 = distinct !DISubprogram(name: "touchKotlin_ObjCExport_WrapExceptionToNSError", scope: !10, file: !10, line: 50, type: !1778, scopeLine: 50, flags: DIFlagPrototyped, spFlags: DISPFlagDefinition | DISPFlagOptimized, unit: !2, retainedNodes: !4)
!1807 = !DILocation(line: 50, column: 1, scope: !1806)
!1808 = distinct !DISubprogram(name: "touchKotlin_ObjCExport_NSErrorAsException", scope: !10, file: !10, line: 51, type: !1778, scopeLine: 51, flags: DIFlagPrototyped, spFlags: DISPFlagDefinition | DISPFlagOptimized, unit: !2, retainedNodes: !4)
!1809 = !DILocation(line: 51, column: 1, scope: !1808)
!1810 = distinct !DISubprogram(name: "touchKotlin_ObjCExport_AllocInstanceWithAssociatedObject", scope: !10, file: !10, line: 52, type: !1778, scopeLine: 52, flags: DIFlagPrototyped, spFlags: DISPFlagDefinition | DISPFlagOptimized, unit: !2, retainedNodes: !4)
!1811 = !DILocation(line: 52, column: 1, scope: !1810)
!1812 = distinct !DISubprogram(name: "touchKotlin_ObjCExport_createContinuationArgument", scope: !10, file: !10, line: 53, type: !1778, scopeLine: 53, flags: DIFlagPrototyped, spFlags: DISPFlagDefinition | DISPFlagOptimized, unit: !2, retainedNodes: !4)
!1813 = !DILocation(line: 53, column: 1, scope: !1812)
!1814 = distinct !DISubprogram(name: "touchKotlin_ObjCExport_createUnitContinuationArgument", scope: !10, file: !10, line: 54, type: !1778, scopeLine: 54, flags: DIFlagPrototyped, spFlags: DISPFlagDefinition | DISPFlagOptimized, unit: !2, retainedNodes: !4)
!1815 = !DILocation(line: 54, column: 1, scope: !1814)
!1816 = distinct !DISubprogram(name: "touchKotlin_ObjCExport_resumeContinuation", scope: !10, file: !10, line: 55, type: !1778, scopeLine: 55, flags: DIFlagPrototyped, spFlags: DISPFlagDefinition | DISPFlagOptimized, unit: !2, retainedNodes: !4)
!1817 = !DILocation(line: 55, column: 1, scope: !1816)
!1818 = distinct !DISubprogram(name: "touchKotlin_ObjCExport_NSIntegerTypeProvider", scope: !10, file: !10, line: 56, type: !1778, scopeLine: 56, flags: DIFlagPrototyped, spFlags: DISPFlagDefinition | DISPFlagOptimized, unit: !2, retainedNodes: !4)
!1819 = !DILocation(line: 56, column: 1, scope: !1818)
!1820 = distinct !DISubprogram(name: "touchKotlin_longTypeProvider", scope: !10, file: !10, line: 57, type: !1778, scopeLine: 57, flags: DIFlagPrototyped, spFlags: DISPFlagDefinition | DISPFlagOptimized, unit: !2, retainedNodes: !4)
!1821 = !DILocation(line: 57, column: 1, scope: !1820)
!1822 = distinct !DISubprogram(name: "impl", linkageName: "_ZN6kotlin14ManuallyScopedINS_10RWSpinLockILNS_24MutexThreadStateHandlingE0EEELb0EE4implEv", scope: !1824, file: !1823, line: 34, type: !1840, scopeLine: 34, flags: DIFlagPrototyped, spFlags: DISPFlagDefinition | DISPFlagOptimized, unit: !939, declaration: !1853, retainedNodes: !1858)
!1823 = !DIFile(filename: "main/cpp/ManuallyScoped.hpp", directory: "/Users/bytedance/KMP/kt2/kotlin_2.0/kotlin-native/runtime/src")
!1824 = distinct !DICompositeType(tag: DW_TAG_class_type, name: "ManuallyScoped<kotlin::RWSpinLock<kotlin::MutexThreadStateHandling::kIgnore>, false>", scope: !944, file: !1823, line: 17, size: 64, flags: DIFlagTypePassByReference, elements: !1825, templateParams: !1855, identifier: "_ZTSN6kotlin14ManuallyScopedINS_10RWSpinLockILNS_24MutexThreadStateHandlingE0EEELb0EEE")
!1825 = !{!1826, !1827, !1831, !1835, !1839, !1842, !1849, !1853, !1854}
!1826 = !DIDerivedType(tag: DW_TAG_inheritance, scope: !1824, baseType: !962, extraData: i32 0)
!1827 = !DIDerivedType(tag: DW_TAG_member, name: "implStorage_", scope: !1824, file: !1823, line: 37, baseType: !1828, size: 64, align: 64)
!1828 = !DICompositeType(tag: DW_TAG_array_type, baseType: !43, size: 64, elements: !1829)
!1829 = !{!1830}
!1830 = !DISubrange(count: 8)
!1831 = !DISubprogram(name: "destroy", linkageName: "_ZN6kotlin14ManuallyScopedINS_10RWSpinLockILNS_24MutexThreadStateHandlingE0EEELb0EE7destroyEv", scope: !1824, file: !1823, line: 26, type: !1832, scopeLine: 26, flags: DIFlagPublic | DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1832 = !DISubroutineType(types: !1833)
!1833 = !{null, !1834}
!1834 = !DIDerivedType(tag: DW_TAG_pointer_type, baseType: !1824, size: 64, flags: DIFlagArtificial | DIFlagObjectPointer)
!1835 = !DISubprogram(name: "operator*", linkageName: "_ZN6kotlin14ManuallyScopedINS_10RWSpinLockILNS_24MutexThreadStateHandlingE0EEELb0EEdeEv", scope: !1824, file: !1823, line: 28, type: !1836, scopeLine: 28, flags: DIFlagPublic | DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1836 = !DISubroutineType(types: !1837)
!1837 = !{!1838, !1834}
!1838 = !DIDerivedType(tag: DW_TAG_reference_type, baseType: !959, size: 64)
!1839 = !DISubprogram(name: "operator->", linkageName: "_ZN6kotlin14ManuallyScopedINS_10RWSpinLockILNS_24MutexThreadStateHandlingE0EEELb0EEptEv", scope: !1824, file: !1823, line: 29, type: !1840, scopeLine: 29, flags: DIFlagPublic | DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1840 = !DISubroutineType(types: !1841)
!1841 = !{!958, !1834}
!1842 = !DISubprogram(name: "operator*", linkageName: "_ZNK6kotlin14ManuallyScopedINS_10RWSpinLockILNS_24MutexThreadStateHandlingE0EEELb0EEdeEv", scope: !1824, file: !1823, line: 30, type: !1843, scopeLine: 30, flags: DIFlagPublic | DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1843 = !DISubroutineType(types: !1844)
!1844 = !{!1845, !1847}
!1845 = !DIDerivedType(tag: DW_TAG_reference_type, baseType: !1846, size: 64)
!1846 = !DIDerivedType(tag: DW_TAG_const_type, baseType: !959)
!1847 = !DIDerivedType(tag: DW_TAG_pointer_type, baseType: !1848, size: 64, flags: DIFlagArtificial | DIFlagObjectPointer)
!1848 = !DIDerivedType(tag: DW_TAG_const_type, baseType: !1824)
!1849 = !DISubprogram(name: "operator->", linkageName: "_ZNK6kotlin14ManuallyScopedINS_10RWSpinLockILNS_24MutexThreadStateHandlingE0EEELb0EEptEv", scope: !1824, file: !1823, line: 31, type: !1850, scopeLine: 31, flags: DIFlagPublic | DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1850 = !DISubroutineType(types: !1851)
!1851 = !{!1852, !1847}
!1852 = !DIDerivedType(tag: DW_TAG_pointer_type, baseType: !1846, size: 64)
!1853 = !DISubprogram(name: "impl", linkageName: "_ZN6kotlin14ManuallyScopedINS_10RWSpinLockILNS_24MutexThreadStateHandlingE0EEELb0EE4implEv", scope: !1824, file: !1823, line: 34, type: !1840, scopeLine: 34, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1854 = !DISubprogram(name: "impl", linkageName: "_ZNK6kotlin14ManuallyScopedINS_10RWSpinLockILNS_24MutexThreadStateHandlingE0EEELb0EE4implEv", scope: !1824, file: !1823, line: 35, type: !1850, scopeLine: 35, flags: DIFlagPrototyped, spFlags: DISPFlagOptimized)
!1855 = !{!1856, !1857}
!1856 = !DITemplateTypeParameter(name: "T", type: !959)
!1857 = !DITemplateValueParameter(name: "kChecked", type: !98, value: i1 false)
!1858 = !{!1859}
!1859 = !DILocalVariable(name: "this", arg: 1, scope: !1822, type: !1860, flags: DIFlagArtificial | DIFlagObjectPointer)
!1860 = !DIDerivedType(tag: DW_TAG_pointer_type, baseType: !1824, size: 64)
!1861 = !DILocation(line: 0, scope: !1822)
!1862 = !DILocation(line: 34, column: 55, scope: !1822)
!1863 = !DILocation(line: 34, column: 48, scope: !1822)
!1864 = distinct !DISubprogram(name: "touchAllocInstance", scope: !1204, file: !1204, line: 35, type: !1778, scopeLine: 35, flags: DIFlagPrototyped, spFlags: DISPFlagDefinition | DISPFlagOptimized, unit: !939, retainedNodes: !4)
!1865 = !DILocation(line: 35, column: 1, scope: !1864)
!1866 = distinct !DISubprogram(name: "touchAllocArrayInstance", scope: !1204, file: !1204, line: 36, type: !1778, scopeLine: 36, flags: DIFlagPrototyped, spFlags: DISPFlagDefinition | DISPFlagOptimized, unit: !939, retainedNodes: !4)
!1867 = !DILocation(line: 36, column: 1, scope: !1866)
!1868 = distinct !DISubprogram(name: "touchInitAndRegisterGlobal", scope: !1204, file: !1204, line: 37, type: !1778, scopeLine: 37, flags: DIFlagPrototyped, spFlags: DISPFlagDefinition | DISPFlagOptimized, unit: !939, retainedNodes: !4)
!1869 = !DILocation(line: 37, column: 1, scope: !1868)
!1870 = distinct !DISubprogram(name: "touchUpdateHeapRef", scope: !1204, file: !1204, line: 38, type: !1778, scopeLine: 38, flags: DIFlagPrototyped, spFlags: DISPFlagDefinition | DISPFlagOptimized, unit: !939, retainedNodes: !4)
!1871 = !DILocation(line: 38, column: 1, scope: !1870)
!1872 = distinct !DISubprogram(name: "touchUpdateStackRef", scope: !1204, file: !1204, line: 39, type: !1778, scopeLine: 39, flags: DIFlagPrototyped, spFlags: DISPFlagDefinition | DISPFlagOptimized, unit: !939, retainedNodes: !4)
!1873 = !DILocation(line: 39, column: 1, scope: !1872)
!1874 = distinct !DISubprogram(name: "touchUpdateVolatileHeapRef", scope: !1204, file: !1204, line: 40, type: !1778, scopeLine: 40, flags: DIFlagPrototyped, spFlags: DISPFlagDefinition | DISPFlagOptimized, unit: !939, retainedNodes: !4)
!1875 = !DILocation(line: 40, column: 1, scope: !1874)
!1876 = distinct !DISubprogram(name: "touchCompareAndSwapVolatileHeapRef", scope: !1204, file: !1204, line: 41, type: !1778, scopeLine: 41, flags: DIFlagPrototyped, spFlags: DISPFlagDefinition | DISPFlagOptimized, unit: !939, retainedNodes: !4)
!1877 = !DILocation(line: 41, column: 1, scope: !1876)
!1878 = distinct !DISubprogram(name: "touchCompareAndSetVolatileHeapRef", scope: !1204, file: !1204, line: 42, type: !1778, scopeLine: 42, flags: DIFlagPrototyped, spFlags: DISPFlagDefinition | DISPFlagOptimized, unit: !939, retainedNodes: !4)
!1879 = !DILocation(line: 42, column: 1, scope: !1878)
!1880 = distinct !DISubprogram(name: "touchGetAndSetVolatileHeapRef", scope: !1204, file: !1204, line: 43, type: !1778, scopeLine: 43, flags: DIFlagPrototyped, spFlags: DISPFlagDefinition | DISPFlagOptimized, unit: !939, retainedNodes: !4)
!1881 = !DILocation(line: 43, column: 1, scope: !1880)
!1882 = distinct !DISubprogram(name: "touchUpdateReturnRef", scope: !1204, file: !1204, line: 44, type: !1778, scopeLine: 44, flags: DIFlagPrototyped, spFlags: DISPFlagDefinition | DISPFlagOptimized, unit: !939, retainedNodes: !4)
!1883 = !DILocation(line: 44, column: 1, scope: !1882)
!1884 = distinct !DISubprogram(name: "touchZeroHeapRef", scope: !1204, file: !1204, line: 45, type: !1778, scopeLine: 45, flags: DIFlagPrototyped, spFlags: DISPFlagDefinition | DISPFlagOptimized, unit: !939, retainedNodes: !4)
!1885 = !DILocation(line: 45, column: 1, scope: !1884)
!1886 = distinct !DISubprogram(name: "touchZeroArrayRefs", scope: !1204, file: !1204, line: 46, type: !1778, scopeLine: 46, flags: DIFlagPrototyped, spFlags: DISPFlagDefinition | DISPFlagOptimized, unit: !939, retainedNodes: !4)
!1887 = !DILocation(line: 46, column: 1, scope: !1886)
!1888 = distinct !DISubprogram(name: "touchEnterFrame", scope: !1204, file: !1204, line: 48, type: !1778, scopeLine: 48, flags: DIFlagPrototyped, spFlags: DISPFlagDefinition | DISPFlagOptimized, unit: !939, retainedNodes: !4)
!1889 = !DILocation(line: 48, column: 1, scope: !1888)
!1890 = distinct !DISubprogram(name: "touchLeaveFrame", scope: !1204, file: !1204, line: 49, type: !1778, scopeLine: 49, flags: DIFlagPrototyped, spFlags: DISPFlagDefinition | DISPFlagOptimized, unit: !939, retainedNodes: !4)
!1891 = !DILocation(line: 49, column: 1, scope: !1890)
!1892 = distinct !DISubprogram(name: "touchSetCurrentFrame", scope: !1204, file: !1204, line: 50, type: !1778, scopeLine: 50, flags: DIFlagPrototyped, spFlags: DISPFlagDefinition | DISPFlagOptimized, unit: !939, retainedNodes: !4)
!1893 = !DILocation(line: 50, column: 1, scope: !1892)
!1894 = distinct !DISubprogram(name: "touchCheckCurrentFrame", scope: !1204, file: !1204, line: 51, type: !1778, scopeLine: 51, flags: DIFlagPrototyped, spFlags: DISPFlagDefinition | DISPFlagOptimized, unit: !939, retainedNodes: !4)
!1895 = !DILocation(line: 51, column: 1, scope: !1894)
!1896 = distinct !DISubprogram(name: "touchMutationCheck", scope: !1204, file: !1204, line: 53, type: !1778, scopeLine: 53, flags: DIFlagPrototyped, spFlags: DISPFlagDefinition | DISPFlagOptimized, unit: !939, retainedNodes: !4)
!1897 = !DILocation(line: 53, column: 1, scope: !1896)
!1898 = distinct !DISubprogram(name: "touchCheckLifetimesConstraint", scope: !1204, file: !1204, line: 54, type: !1778, scopeLine: 54, flags: DIFlagPrototyped, spFlags: DISPFlagDefinition | DISPFlagOptimized, unit: !939, retainedNodes: !4)
!1899 = !DILocation(line: 54, column: 1, scope: !1898)
!1900 = distinct !DISubprogram(name: "touchFreezeSubgraph", scope: !1204, file: !1204, line: 55, type: !1778, scopeLine: 55, flags: DIFlagPrototyped, spFlags: DISPFlagDefinition | DISPFlagOptimized, unit: !939, retainedNodes: !4)
!1901 = !DILocation(line: 55, column: 1, scope: !1900)
!1902 = distinct !DISubprogram(name: "touchCheckGlobalsAccessible", scope: !1204, file: !1204, line: 56, type: !1778, scopeLine: 56, flags: DIFlagPrototyped, spFlags: DISPFlagDefinition | DISPFlagOptimized, unit: !939, retainedNodes: !4)
!1903 = !DILocation(line: 56, column: 1, scope: !1902)
!1904 = distinct !DISubprogram(name: "touchLookupInterfaceTableRecord", scope: !1204, file: !1204, line: 58, type: !1778, scopeLine: 58, flags: DIFlagPrototyped, spFlags: DISPFlagDefinition | DISPFlagOptimized, unit: !939, retainedNodes: !4)
!1905 = !DILocation(line: 58, column: 1, scope: !1904)
!1906 = distinct !DISubprogram(name: "touchIsSubtype", scope: !1204, file: !1204, line: 59, type: !1778, scopeLine: 59, flags: DIFlagPrototyped, spFlags: DISPFlagDefinition | DISPFlagOptimized, unit: !939, retainedNodes: !4)
!1907 = !DILocation(line: 59, column: 1, scope: !1906)
!1908 = distinct !DISubprogram(name: "touchIsSubclassFast", scope: !1204, file: !1204, line: 60, type: !1778, scopeLine: 60, flags: DIFlagPrototyped, spFlags: DISPFlagDefinition | DISPFlagOptimized, unit: !939, retainedNodes: !4)
!1909 = !DILocation(line: 60, column: 1, scope: !1908)
!1910 = distinct !DISubprogram(name: "touchThrowException", scope: !1204, file: !1204, line: 62, type: !1778, scopeLine: 62, flags: DIFlagPrototyped, spFlags: DISPFlagDefinition | DISPFlagOptimized, unit: !939, retainedNodes: !4)
!1911 = !DILocation(line: 62, column: 1, scope: !1910)
!1912 = distinct !DISubprogram(name: "touchKotlin_getExceptionObject", scope: !1204, file: !1204, line: 63, type: !1778, scopeLine: 63, flags: DIFlagPrototyped, spFlags: DISPFlagDefinition | DISPFlagOptimized, unit: !939, retainedNodes: !4)
!1913 = !DILocation(line: 63, column: 1, scope: !1912)
!1914 = distinct !DISubprogram(name: "touchAppendToInitializersTail", scope: !1204, file: !1204, line: 65, type: !1778, scopeLine: 65, flags: DIFlagPrototyped, spFlags: DISPFlagDefinition | DISPFlagOptimized, unit: !939, retainedNodes: !4)
!1915 = !DILocation(line: 65, column: 1, scope: !1914)
!1916 = distinct !DISubprogram(name: "touchCallInitGlobalPossiblyLock", scope: !1204, file: !1204, line: 66, type: !1778, scopeLine: 66, flags: DIFlagPrototyped, spFlags: DISPFlagDefinition | DISPFlagOptimized, unit: !939, retainedNodes: !4)
!1917 = !DILocation(line: 66, column: 1, scope: !1916)
!1918 = distinct !DISubprogram(name: "touchCallInitThreadLocal", scope: !1204, file: !1204, line: 67, type: !1778, scopeLine: 67, flags: DIFlagPrototyped, spFlags: DISPFlagDefinition | DISPFlagOptimized, unit: !939, retainedNodes: !4)
!1919 = !DILocation(line: 67, column: 1, scope: !1918)
!1920 = distinct !DISubprogram(name: "touchAddTLSRecord", scope: !1204, file: !1204, line: 69, type: !1778, scopeLine: 69, flags: DIFlagPrototyped, spFlags: DISPFlagDefinition | DISPFlagOptimized, unit: !939, retainedNodes: !4)
!1921 = !DILocation(line: 69, column: 1, scope: !1920)
!1922 = distinct !DISubprogram(name: "touchLookupTLS", scope: !1204, file: !1204, line: 70, type: !1778, scopeLine: 70, flags: DIFlagPrototyped, spFlags: DISPFlagDefinition | DISPFlagOptimized, unit: !939, retainedNodes: !4)
!1923 = !DILocation(line: 70, column: 1, scope: !1922)
!1924 = distinct !DISubprogram(name: "touchKotlin_initRuntimeIfNeeded", scope: !1204, file: !1204, line: 72, type: !1778, scopeLine: 72, flags: DIFlagPrototyped, spFlags: DISPFlagDefinition | DISPFlagOptimized, unit: !939, retainedNodes: !4)
!1925 = !DILocation(line: 72, column: 1, scope: !1924)
!1926 = distinct !DISubprogram(name: "touchKRefSharedHolder_initLocal", scope: !1204, file: !1204, line: 74, type: !1778, scopeLine: 74, flags: DIFlagPrototyped, spFlags: DISPFlagDefinition | DISPFlagOptimized, unit: !939, retainedNodes: !4)
!1927 = !DILocation(line: 74, column: 1, scope: !1926)
!1928 = distinct !DISubprogram(name: "touchKRefSharedHolder_init", scope: !1204, file: !1204, line: 75, type: !1778, scopeLine: 75, flags: DIFlagPrototyped, spFlags: DISPFlagDefinition | DISPFlagOptimized, unit: !939, retainedNodes: !4)
!1929 = !DILocation(line: 75, column: 1, scope: !1928)
!1930 = distinct !DISubprogram(name: "touchKRefSharedHolder_dispose", scope: !1204, file: !1204, line: 76, type: !1778, scopeLine: 76, flags: DIFlagPrototyped, spFlags: DISPFlagDefinition | DISPFlagOptimized, unit: !939, retainedNodes: !4)
!1931 = !DILocation(line: 76, column: 1, scope: !1930)
!1932 = distinct !DISubprogram(name: "touchKRefSharedHolder_ref", scope: !1204, file: !1204, line: 77, type: !1778, scopeLine: 77, flags: DIFlagPrototyped, spFlags: DISPFlagDefinition | DISPFlagOptimized, unit: !939, retainedNodes: !4)
!1933 = !DILocation(line: 77, column: 1, scope: !1932)
!1934 = distinct !DISubprogram(name: "touchKotlin_mm_switchThreadStateNative", scope: !1204, file: !1204, line: 79, type: !1778, scopeLine: 79, flags: DIFlagPrototyped, spFlags: DISPFlagDefinition | DISPFlagOptimized, unit: !939, retainedNodes: !4)
!1935 = !DILocation(line: 79, column: 1, scope: !1934)
!1936 = distinct !DISubprogram(name: "touchKotlin_mm_switchThreadStateNative_debug", scope: !1204, file: !1204, line: 80, type: !1778, scopeLine: 80, flags: DIFlagPrototyped, spFlags: DISPFlagDefinition | DISPFlagOptimized, unit: !939, retainedNodes: !4)
!1937 = !DILocation(line: 80, column: 1, scope: !1936)
!1938 = distinct !DISubprogram(name: "touchKotlin_mm_switchThreadStateRunnable", scope: !1204, file: !1204, line: 81, type: !1778, scopeLine: 81, flags: DIFlagPrototyped, spFlags: DISPFlagDefinition | DISPFlagOptimized, unit: !939, retainedNodes: !4)
!1939 = !DILocation(line: 81, column: 1, scope: !1938)
!1940 = distinct !DISubprogram(name: "touchKotlin_mm_switchThreadStateRunnable_debug", scope: !1204, file: !1204, line: 82, type: !1778, scopeLine: 82, flags: DIFlagPrototyped, spFlags: DISPFlagDefinition | DISPFlagOptimized, unit: !939, retainedNodes: !4)
!1941 = !DILocation(line: 82, column: 1, scope: !1940)
!1942 = distinct !DISubprogram(name: "touchKotlin_mm_safePointFunctionPrologue", scope: !1204, file: !1204, line: 83, type: !1778, scopeLine: 83, flags: DIFlagPrototyped, spFlags: DISPFlagDefinition | DISPFlagOptimized, unit: !939, retainedNodes: !4)
!1943 = !DILocation(line: 83, column: 1, scope: !1942)
!1944 = distinct !DISubprogram(name: "touchKotlin_mm_safePointWhileLoopBody", scope: !1204, file: !1204, line: 84, type: !1778, scopeLine: 84, flags: DIFlagPrototyped, spFlags: DISPFlagDefinition | DISPFlagOptimized, unit: !939, retainedNodes: !4)
!1945 = !DILocation(line: 84, column: 1, scope: !1944)
!1946 = distinct !DISubprogram(name: "touchKotlin_processObjectInMark", scope: !1204, file: !1204, line: 86, type: !1778, scopeLine: 86, flags: DIFlagPrototyped, spFlags: DISPFlagDefinition | DISPFlagOptimized, unit: !939, retainedNodes: !4)
!1947 = !DILocation(line: 86, column: 1, scope: !1946)
!1948 = distinct !DISubprogram(name: "touchKotlin_processArrayInMark", scope: !1204, file: !1204, line: 87, type: !1778, scopeLine: 87, flags: DIFlagPrototyped, spFlags: DISPFlagDefinition | DISPFlagOptimized, unit: !939, retainedNodes: !4)
!1949 = !DILocation(line: 87, column: 1, scope: !1948)
!1950 = distinct !DISubprogram(name: "touchKotlin_processEmptyObjectInMark", scope: !1204, file: !1204, line: 88, type: !1778, scopeLine: 88, flags: DIFlagPrototyped, spFlags: DISPFlagDefinition | DISPFlagOptimized, unit: !939, retainedNodes: !4)
!1951 = !DILocation(line: 88, column: 1, scope: !1950)
!1952 = distinct !DISubprogram(name: "touchKotlin_arrayGetElementAddress", scope: !1204, file: !1204, line: 90, type: !1778, scopeLine: 90, flags: DIFlagPrototyped, spFlags: DISPFlagDefinition | DISPFlagOptimized, unit: !939, retainedNodes: !4)
!1953 = !DILocation(line: 90, column: 1, scope: !1952)
!1954 = distinct !DISubprogram(name: "touchKotlin_intArrayGetElementAddress", scope: !1204, file: !1204, line: 91, type: !1778, scopeLine: 91, flags: DIFlagPrototyped, spFlags: DISPFlagDefinition | DISPFlagOptimized, unit: !939, retainedNodes: !4)
!1955 = !DILocation(line: 91, column: 1, scope: !1954)
!1956 = distinct !DISubprogram(name: "touchKotlin_longArrayGetElementAddress", scope: !1204, file: !1204, line: 92, type: !1778, scopeLine: 92, flags: DIFlagPrototyped, spFlags: DISPFlagDefinition | DISPFlagOptimized, unit: !939, retainedNodes: !4)
!1957 = !DILocation(line: 92, column: 1, scope: !1956)
