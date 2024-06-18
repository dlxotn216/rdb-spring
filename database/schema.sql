drop table if exists app_coupon;
create table app_coupon
(
  coupon_key  bigint generated by default as identity,
  coupon_id   text        not null,
  name        text        not null,
  description text,

  created_at  timestamptz not null,
  created_by  bigint      not null,
  modified_at timestamptz not null,
  modified_by bigint      not null,
  primary key (coupon_key)
);
alter table app_coupon
  add constraint app_coupon_ux_01 unique (coupon_id);